package data;

/**
 * @Author stormbroken
 * Create by 2021/07/09
 * @Version 1.0
 **/

public class SystemInfo {
    /**
     * The usage rate of memory
     */
    private Double memoryUsage;
    /**
     * The usage rate of cpu
     */
    private Double cpuUsage;
    /**
     * The usage rate of disk
     */
    private Double diskUsage;
    /**
     * timestamp
     */
    private Long time;

    public Double getMemoryUsage() {
        return memoryUsage;
    }

    public void setMemoryUsage(Double memoryUsage) {
        this.memoryUsage = memoryUsage;
    }

    public Double getCpuUsage() {
        return cpuUsage;
    }

    public void setCpuUsage(Double cpuUsage) {
        this.cpuUsage = cpuUsage;
    }

    public Double getDiskUsage() {
        return diskUsage;
    }

    public void setDiskUsage(Double diskUsage) {
        this.diskUsage = diskUsage;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "MyTest{" +
                "memoryUsage=" + memoryUsage +
                ", cpuUsage=" + cpuUsage +
                ", diskUsage=" + diskUsage +
                ", time=" + time +
                '}';
    }
}
