package br.edu.ufabc.padm.pocketmentaltest.model;

import java.io.Serializable;
import java.util.Date;

public class Patient implements Serializable {

    private long id;
    private String name;
    private String address;
    private Date birthday;
    private int schooling;
    private int sex;
    private String susCard;
    private String phone;

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public Date getBirthday() {
        return birthday;
    }
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public int getSchooling() {
        return schooling;
    }
    public void setSchooling(int schooling) {
        this.schooling = schooling;
    }

    public int getSex() {
        return sex;
    }
    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getSusCard() {
        return susCard;
    }
    public void setSusCard(String susCard) {
        this.susCard = susCard;
    }

    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
}
