package executor;

import java.util.Map;

/**
 * @Author stormbroken
 * Create by 2021/07/09
 * @Version 1.0
 **/

public interface Executor {
    /**
     * 执行列表查询
     *
     * @param <T> 结果泛型
     * @param query 查询语句
     * @param resultType 结果类型
     * @param map 映射类型
     * @return
     */
    <T> T queryList(String query, Class<?> resultType, Map<String, String> map);
}
