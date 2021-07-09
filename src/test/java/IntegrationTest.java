import data.MyTest;
import mapper.MyTestMapper;
import org.junit.Test;
import sqlSession.MySqlSession;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @Author stormbroken
 * Create by 2021/07/09
 * @Version 1.0
 **/

public class IntegrationTest {
    @Test
    public void queryAll(){
        MySqlSession mySqlSession = new MySqlSession();
        MyTestMapper mapper = mySqlSession.getMapper(MyTestMapper.class);
        List<MyTest> myTests = mapper.getMyTests();
        assertNotNull(myTests);
        assertNotEquals(0, myTests.size());
    }
}
