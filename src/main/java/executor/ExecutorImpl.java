package executor;

import sqlSession.MyConfiguration;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author stormbroken
 * Create by 2021/07/09
 * @Version 1.0
 **/

public class ExecutorImpl implements Executor{
    private MyConfiguration myConfiguration = new MyConfiguration();

    @Override
    public <T> T queryList(String query, Class<?> resultType, Map<String, String> map) {
        Connection connection = getConnection();
        ResultSet resultSet = null;
        Statement statement = null;
        try{
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            List<Object> objects = new ArrayList<>();
            while(resultSet.next()){
                Object object = resultType.newInstance();
                Field fields[] = resultType.getDeclaredFields();
                for(Field field: fields){
                    Constructor<?> constructor = field.getType().getConstructor(String.class);
                    // 可以访问私有变量
                    field.setAccessible(true);
                    // 修改变量的值
                    field.set(object, constructor.newInstance(
                            resultSet.getString(
                                    map.get(field.getName()))));
                }
                objects.add(object);
            }
            return (T) objects;
        }catch (SQLException | IllegalAccessException | InstantiationException
                | InvocationTargetException | NoSuchMethodException e){
            e.printStackTrace();
        } finally {
            try {
                if(resultSet != null){
                    resultSet.close();
                }
                if(statement != null){
                    statement.close();
                }
                if(connection != null) {
                    connection.close();
                }
            }catch (Exception e2){
                e2.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 创建新的数据库连接
     * @return
     */
    private Connection getConnection(){
        try{
            Connection connection = myConfiguration.build("Config.xml");
            return connection;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
