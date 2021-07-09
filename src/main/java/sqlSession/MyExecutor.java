package sqlSession;

import data.MyTest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author stormbroken
 * Create by 2021/07/09
 * @Version 1.0
 **/

public class MyExecutor implements Executor{
    private MyConfiguration myConfiguration = new MyConfiguration();

    @Override
    public <T> T query(String sql, Object parameter) {
        Connection connection = getConnection();
        ResultSet resultSet = null;
        PreparedStatement pre = null;
        try{
            pre = connection.prepareStatement(sql);
            // TODO 设置参数
            // pre.setString(1, parameter.toString());
            resultSet = pre.executeQuery();
            // TODO 使用反射
            List<MyTest> myTests = new ArrayList<>();
            while(resultSet.next()){
                MyTest myTest = new MyTest();
                myTest.setTime(Long.valueOf(resultSet.getString(1)));
                myTest.setDiskUsage(Double.valueOf(resultSet.getString(2)));
                myTest.setMemoryUsage(Double.valueOf(resultSet.getString(3)));
                myTest.setCpuUsage(Double.valueOf(resultSet.getString(4)));
                myTests.add(myTest);
            }
            return (T) myTests;
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try {
                if(resultSet != null){
                    resultSet.close();
                }
                if(pre != null){
                    pre.close();
                }
                // TODO 池化连接
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
