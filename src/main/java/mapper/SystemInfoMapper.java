package mapper;

import data.SystemInfo;

import java.util.List;

/**
 * @Author stormbroken
 * Create by 2021/07/09
 * @Version 1.0
 **/

public interface SystemInfoMapper {
    /**
     * get all records of SystemInfo
     * @return
     */
    List<SystemInfo> getAllSystemInfo();
}
