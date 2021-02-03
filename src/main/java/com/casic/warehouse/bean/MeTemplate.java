package com.casic.warehouse.bean;

public class MeTemplate {
    public String name;
    public String context;
    public String phone;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "MeTemplate{" +
                "name='" + name + '\'' +
                ", context='" + context + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
