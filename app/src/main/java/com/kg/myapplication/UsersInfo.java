package com.kg.myapplication;

public class UsersInfo {

    private String mail;
    private String phone;

    public UsersInfo(){ }

    UsersInfo(String mail,String phone){
    this.mail=mail;
    this.phone=phone;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
