package sqlSession;

import executor.Executor;
import executor.ExecutorImpl;

import java.lang.reflect.Proxy;
import java.util.Map;

/**
 * @Author stormbroken
 * Create by 2021/07/09
 * @Version 1.0
 **/

public class MySqlSession {
    private Executor executor = new ExecutorImpl();
    private MyConfiguration myConfiguration = new MyConfiguration();

    /**
     * select data from statement
     * @param statement
     * @param result
     * @param map
     * @param <T>
     * @return
     */
    public <T> T selectAll(String statement, Class<?> result, Map<String, String> map){
        return executor.queryList(statement, result, map);
    }

    /**
     * Dynamic proxy call
     * @param clas
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    public <T> T getMapper(Class<T> clas){
        return (T) Proxy.newProxyInstance(clas.getClassLoader(), new Class[]{clas},
                new MyMapperProxy(this, myConfiguration));
    }
}
