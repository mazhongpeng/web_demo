package com.southwind.entity;

public class DormitoryAdmin {
    private int id;
    private String username;
    private String password;
    private String name;
    private String sex;
    private String telephone;

    public DormitoryAdmin(int id, String username, String password, String name, String sex, String telephone) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.sex = sex;
        this.telephone = telephone;
    }

    public DormitoryAdmin(String username, String password, String name, String sex, String telephone) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.sex = sex;
        this.telephone = telephone;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getSex() {
        return sex;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    @Override
    public String toString() {
        return "DormitoryAdmin{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", telephone='" + telephone + '\'' +
                '}';
    }
}
