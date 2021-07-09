package config;

import java.util.Map;

/**
 * @Author stormbroken
 * Create by 2021/07/09
 * @Version 1.0
 **/

public class Function {
    /**
     * Type Of SQL
     */
    private String sqlType;
    /**
     * The name of Function
     */
    private String funcName;
    /**
     * The Value of SQL
     */
    private String sql;
    /**
     * The Type of Result
     * TODO how to differ single one and list
     * First it will be String(Because of possibility of alias)
     * Finally it will be Class<?> which relates to the result
     */
    private Object resultType;
    /**
     * The Map between the POJO and the metadata of database
     * Eg cpuUsage -> root.demo.pc1.cpu
     */
    private Map<String, String> resultMap;

    public String getSqlType() {
        return sqlType;
    }

    public void setSqlType(String sqlType) {
        this.sqlType = sqlType;
    }

    public String getFuncName() {
        return funcName;
    }

    public void setFuncName(String funcName) {
        this.funcName = funcName;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public Object getResultType() {
        return resultType;
    }

    public void setResultType(Object resultType) {
        this.resultType = resultType;
    }

    public Map<String, String> getResultMap() {
        return resultMap;
    }

    public void setResultMap(Map<String, String> resultMap) {
        this.resultMap = resultMap;
    }

    // TODO extend params
}
