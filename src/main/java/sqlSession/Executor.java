package sqlSession;

/**
 * @Author stormbroken
 * Create by 2021/07/09
 * @Version 1.0
 **/

public interface Executor {
    public <T> T query(String sql, Object parameter);
}
