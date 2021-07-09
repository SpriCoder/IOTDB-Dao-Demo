package sqlSession;

import java.lang.reflect.Proxy;

/**
 * @Author stormbroken
 * Create by 2021/07/09
 * @Version 1.0
 **/

public class MySqlSession {
    private Executor executor = new MyExecutor();
    private MyConfiguration myConfiguration = new MyConfiguration();

    public <T> T selectOne(String statement, Object parameter){
        return executor.query(statement, parameter);
    }

    /**
     * 动态代理调用
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
