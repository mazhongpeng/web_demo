package com.southwind.entity;

public class Student {
    private Integer id;
    private String number;
    private String name;
    private String sex;
    private Integer dormitoryId;

    private String dormitoryName;
    private String state;
    private String createDate;

    public Student(Integer id, String number, String name, String sex, Integer dormitoryId) {
        this.id = id;
        this.number = number;
        this.name = name;
        this.sex = sex;
        this.dormitoryId = dormitoryId;
    }

    public Student(Integer id, String number, String name, String sex, Integer dormitoryId, String dormitoryName, String state, String createDate) {
        this.id = id;
        this.number = number;
        this.name = name;
        this.sex = sex;
        this.dormitoryId = dormitoryId;
        this.dormitoryName = dormitoryName;
        this.state = state;
        this.createDate = createDate;
    }
    public Student( String number, String name, String sex, Integer dormitoryId) {
        this.number = number;
        this.name = name;
        this.sex = sex;
        this.dormitoryId = dormitoryId;

    }

    public Student(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Student(Integer id, String number, String name, String sex, Integer dormitoryId, String dormitoryName, String state) {
        this.id = id;
        this.number = number;
        this.name = name;
        this.sex = sex;
        this.dormitoryId = dormitoryId;
        this.dormitoryName = dormitoryName;
        this.state = state;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", dormitoryId='" + dormitoryId + '\'' +
                ", dormitoryName='" + dormitoryName + '\'' +
                ", state='" + state + '\'' +
                ", createDate='" + createDate + '\'' +
                '}';
    }
}
