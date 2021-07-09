package sqlSession;

import config.Function;
import config.MapperBean;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.List;

/**
 * @Author stormbroken
 * Create by 2021/07/09
 * @Version 1.0
 **/

public class MyMapperProxy implements InvocationHandler {

    private MySqlSession mySqlSession;

    private MyConfiguration myConfiguration;

    public MyMapperProxy(MySqlSession mySqlSession, MyConfiguration myConfiguration) {
        this.mySqlSession = mySqlSession;
        this.myConfiguration = myConfiguration;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        MapperBean mapperBean = myConfiguration.readMapper("SystemInfoMapper.xml");
        if(!method.getDeclaringClass().getName().equals(mapperBean.getInterfaceName())){
            return null;
        }

        List<Function> functionList = mapperBean.getFunctions();
        if(null != functionList || 0 != functionList.size()){
            for(Function function: functionList){
                if(method.getName().equals(function.getFuncName())){
                    return mySqlSession.selectAll(function.getSql(),
                            (Class<?>) function.getResultType(), function.getResultMap());
                }
            }
        }
        return null;
    }
}
