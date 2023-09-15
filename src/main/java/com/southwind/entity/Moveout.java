package com.southwind.entity;

public class Moveout {



    private Integer id;
    private Integer studentId;
    private String studentName;
    private Integer dormitoryId;
    private String dormitoryName;
    private String reason;
    private String createDate;

    public Moveout(Integer studentId, Integer dormitoryId, String reason, String createDate) {
        this.studentId = studentId;
        this.dormitoryId = dormitoryId;
        this.reason = reason;
        this.createDate = createDate;
    }

    public Moveout(Integer id, String studentName, String dormitoryName, String reason, String createDate) {
        this.id = id;
        this.studentName = studentName;
        this.dormitoryName = dormitoryName;
        this.reason = reason;
        this.createDate = createDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public Integer getDormitoryId() {
        return dormitoryId;
    }

    public void setDormitoryId(Integer dormitoryId) {
        this.dormitoryId = dormitoryId;
    }

    public String getDormitoryName() {
        return dormitoryName;
    }

    public void setDormitoryName(String dormitoryName) {
        this.dormitoryName = dormitoryName;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "Moveout{" +
                "id=" + id +
                ", studentId=" + studentId +
                ", studentName='" + studentName + '\'' +
                ", dormitoryId=" + dormitoryId +
                ", dormitoryName='" + dormitoryName + '\'' +
                ", reason='" + reason + '\'' +
                ", createDate='" + createDate + '\'' +
                '}';
    }
}
