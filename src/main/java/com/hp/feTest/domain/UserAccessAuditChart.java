package com.hp.feTest.domain;

public class UserAccessAuditChart {
    
    private String time;
    
    private String dataSet1;
    
    private String dataSet2;
    
    private String dataSet3;
    
    private String dataSet4;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDataSet1() {
        return dataSet1;
    }

    public void setDataSet1(String dataSet1) {
        this.dataSet1 = dataSet1;
    }

    public String getDataSet2() {
        return dataSet2;
    }

    public void setDataSet2(String dataSet2) {
        this.dataSet2 = dataSet2;
    }

    public String getDataSet3() {
        return dataSet3;
    }

    public void setDataSet3(String dataSet3) {
        this.dataSet3 = dataSet3;
    }

    public String getDataSet4() {
        return dataSet4;
    }

    public void setDataSet4(String dataSet4) {
        this.dataSet4 = dataSet4;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserAccessAuditChart)) return false;

        UserAccessAuditChart that = (UserAccessAuditChart) o;

        if (dataSet1 != null ? !dataSet1.equals(that.dataSet1) : that.dataSet1 != null) return false;
        if (dataSet2 != null ? !dataSet2.equals(that.dataSet2) : that.dataSet2 != null) return false;
        if (dataSet3 != null ? !dataSet3.equals(that.dataSet3) : that.dataSet3 != null) return false;
        if (dataSet4 != null ? !dataSet4.equals(that.dataSet4) : that.dataSet4 != null) return false;
        if (time != null ? !time.equals(that.time) : that.time != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = time != null ? time.hashCode() : 0;
        result = 31 * result + (dataSet1 != null ? dataSet1.hashCode() : 0);
        result = 31 * result + (dataSet2 != null ? dataSet2.hashCode() : 0);
        result = 31 * result + (dataSet3 != null ? dataSet3.hashCode() : 0);
        result = 31 * result + (dataSet4 != null ? dataSet4.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "UserAccessAuditChart{" +
                "time='" + time + '\'' +
                ", dataSet1='" + dataSet1 + '\'' +
                ", dataSet2='" + dataSet2 + '\'' +
                ", dataSet3='" + dataSet3 + '\'' +
                ", dataSet4='" + dataSet4 + '\'' +
                '}';
    }
}
