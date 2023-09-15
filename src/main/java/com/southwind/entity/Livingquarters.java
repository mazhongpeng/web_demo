package com.southwind.entity;

public class Livingquarters {
    private Integer id;
    private Integer buildingId;
    private String buildingName;
    private String name;
    private String type;
    private String available;
    private String telephone;

    public Livingquarters(Integer id,String name) {
        this.id = id;
        this.name = name;
    }

    public Livingquarters(Integer id, String buildingName, String name, String type, String available, String telephone) {
        this.id = id;
        this.buildingName = buildingName;
        this.name = name;
        this.type = type;
        this.available = available;
        this.telephone = telephone;
    }

    public Livingquarters(Integer id, String name, String telephone) {
        this.id = id;
        this.name = name;
        this.telephone = telephone;
    }

    public Livingquarters(Integer buildingId, String name, String type, String available, String telephone) {
        this.buildingId = buildingId;
        this.name = name;
        this.type = type;
        this.available = available;
        this.telephone = telephone;
    }

    public Livingquarters(Integer id) {
        this.id = id;
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(Integer buildingId) {
        this.buildingId = buildingId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    @Override
    public String toString() {
        return "Livingquarters{" +
                "id=" + id +
                ", buildingId=" + buildingId +
                ", buildingName='" + buildingName + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", available='" + available + '\'' +
                ", telephone='" + telephone + '\'' +
                '}';
    }
}
