package com.southwind.entity;

public class Building {
    private Integer id;
    private String name;
    private String introduction;
    private String adminName;
    private Integer adminId;


    public Building(Integer id, String name, String introduction, String adminName, Integer adminId) {
        this.id = id;
        this.name = name;
        this.introduction = introduction;
        this.adminName = adminName;
        this.adminId = adminId;
    }

    public Building(Integer id, String name, String introduction, Integer adminId) {
        this.id = id;
        this.name = name;
        this.introduction = introduction;
        this.adminId = adminId;
    }

    public Building(String name, String introduction, Integer adminId) {
        this.name = name;
        this.introduction = introduction;
        this.adminId = adminId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    @Override
    public String toString() {
        return "Building{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", introduction='" + introduction + '\'' +
                ", adminId=" + adminId +
                ", adminName='" + adminName + '\'' +
                '}';
    }
}
