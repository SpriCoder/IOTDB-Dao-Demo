package executor;

import java.util.Map;

/**
 * @Author stormbroken
 * Create by 2021/07/09
 * @Version 1.0
 **/

public interface Executor {
    /**
     *
     * @param <T>
     * @param query
     * @param resultType 结果类型
     * @param map 映射类型
     * @return
     */
    <T> T queryList(String query, Class<?> resultType, Map<String, String> map);
}
