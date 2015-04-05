package com.example.phobos.contacts;

public class Contact {
    private String id;
    private String name;
    private String phone;
    private String avatar;

    public Contact(String id, String name, String phone, String avatar) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.avatar = avatar;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
