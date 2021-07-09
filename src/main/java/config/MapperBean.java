package config;

import java.util.List;
import java.util.Map;

/**
 * @Author stormbroken
 * Create by 2021/07/09
 * @Version 1.0
 **/

public class MapperBean {
    /**
     * the name of interface
     */
    private String interfaceName;
    /**
     * the methods in the interface
     */
    private List<Function> functions;

    /**
     * the map between POJO and database, POJO -> database
     */
    private Map<String, String> paramMap;

    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    public List<Function> getFunctions() {
        return functions;
    }

    public void setFunctions(List<Function> functions) {
        this.functions = functions;
    }

    public Map<String, String> getParamMap() {
        return paramMap;
    }

    public void setParamMap(Map<String, String> paramMap) {
        this.paramMap = paramMap;
    }
}
