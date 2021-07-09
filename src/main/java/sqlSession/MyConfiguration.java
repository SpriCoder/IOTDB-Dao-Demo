package sqlSession;

import config.Function;
import config.MapperBean;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;
import java.util.List;

/**
 * @Author stormbroken
 * Create by 2021/07/09
 * @Version 1.0
 **/

public class MyConfiguration {
    private static ClassLoader loader = ClassLoader.getSystemClassLoader();

    /**
     * Read the configuration from xml, and generate connection
     * @param resource
     * @return
     */
    public Connection build(String resource){
        try{
            InputStream inputStream = loader.getResourceAsStream(resource);
            SAXReader reader = new SAXReader();
            Document document = reader.read(inputStream);
            Element root = document.getRootElement();
            return evalDataSource(root);
        }catch (Exception e){
            throw new RuntimeException("Error occurs while eval xml: " + resource);
        }
    }

    /**
     * eval one node of database
     * @param node
     * @return
     * @throws ClassNotFoundException
     */
    private Connection evalDataSource(Element node) throws ClassNotFoundException{
        if(!node.getName().equals("database")){
            throw new RuntimeException("Root should be <database>");
        }

        String driverClassName = null;
        String url = null;
        String username = null;
        String password = null;
        // 获取属性节点
        for(Object item: node.elements("property")){
            Element i = (Element) item;
            String value = getValue(i);
            String name = i.attributeValue("name");
            if(name == null || value == null){
                throw new RuntimeException("[Database: <property> should contain name.");
            }
            // 赋值
            switch (name){
                case "url": url = value; break;
                case "username": username = value; break;
                case "driverClassName": driverClassName = value; break;
                case "password": password = value; break;
                default: throw new RuntimeException("[database]: <property> unknown name: " + name);
            }
        }
        Class.forName(driverClassName);
        Connection connection = null;
        try{
            connection = DriverManager.getConnection(url, username, password);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return connection;
    }

    private String getValue(Element node){
        return node.hasContent() ? node.getText() : node.attributeValue("value");
    }

    /**
     * generate mapper from xml
     * @param path
     * @return
     */
    @SuppressWarnings("rawTypes")
    public MapperBean readMapper(String path){
        MapperBean mapper = new MapperBean();
        try{
            InputStream inputStream = loader.getResourceAsStream(path);
            SAXReader reader = new SAXReader();
            Document document = reader.read(inputStream);
            Element root = document.getRootElement();

            // set the name of interface to the name of mapper
            mapper.setInterfaceName(root.attributeValue("nameSpace").trim());

            // map alias -> Map<column, property>
            Map<String, Map<String, String>> resultTypeMap = new HashMap<>();
            // map alias_resultMap id -> resultMap name
            Map<String, String> resultNameMap = new HashMap<>();

            // the list of methods
            List<Function> functionList = new ArrayList<>();
            for(Iterator rootIter = root.elementIterator(); rootIter.hasNext();){
                Element e = (Element) rootIter.next();

                switch (e.getName()){
                    case "resultMap":
                        Map<String, String> paramMap = new HashMap<>();
                        for(Iterator resultIter = e.elementIterator(); resultIter.hasNext();){
                            Element result = (Element) resultIter.next();
                            paramMap.put(result.attributeValue("column"),
                                    result.attributeValue("property"));
                        }
                        resultTypeMap.put(e.attributeValue("id"), paramMap);
                        resultNameMap.put(e.attributeValue("id"), e.attributeValue("resultType"));
                        break;
                    default:
                        Function function = new Function();
                        String sqlType = e.getName().trim();
                        String funcName = e.attributeValue("id").trim();
                        String sql = e.getText().trim();
                        String resultType = e.attributeValue("resultType").trim();

                        function.setSqlType(sqlType);
                        function.setFuncName(funcName);
                        function.setResultType(resultType);
                        function.setSql(sql);
                        functionList.add(function);
                        break;
                }
            }
            for(Function function: functionList){
                String resultType = (String) function.getResultType();
                function.setResultMap(resultTypeMap.get(resultType));
                try{
                    function.setResultType(Class.forName(resultType));
                }catch (ClassNotFoundException e1){
                    try{
                        resultType = resultNameMap.get(resultType);
                        function.setResultType(Class.forName(resultType));
                    }catch (ClassNotFoundException e2){
                        throw new RuntimeException("[mapper]: unknown resultType", e2);
                    }
                }
            }
            mapper.setFunctions(functionList);
        }catch (DocumentException e){
            e.printStackTrace();
        }
        return mapper;
    }
}
