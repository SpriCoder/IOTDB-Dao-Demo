package data;

/**
 * @Author stormbroken
 * Create by 2021/07/09
 * @Version 1.0
 **/

public class SystemInfo {
    private Double memoryUsage;
    private Double cpuUsage;
    private Double diskUsage;
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
