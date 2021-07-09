import data.SystemInfo;
import mapper.SystemInfoMapper;
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
        SystemInfoMapper mapper = mySqlSession.getMapper(SystemInfoMapper.class);
        List<SystemInfo> systemInfos = mapper.getMyTests();
        for(SystemInfo systemInfo: systemInfos){
            System.out.println(systemInfo);
        }
        assertNotNull(systemInfos);
        assertNotEquals(0, systemInfos.size());
    }
}
