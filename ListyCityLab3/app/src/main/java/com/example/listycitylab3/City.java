package com.example.listycitylab3;

import java.io.Serializable;

public class City implements Serializable {

    private String name;
    private String province;

    public City(String name, String province) {
        this.name = name;
        this.province = province;
    }

    // Getter: get the city name and province
    public String getName() {
        return name;
    }

    public String getProvince() {
        return province;
    }

    // Setter: needed when we are editing the city
    public void setName(String name) {
        this.name = name;
    }

    public void setProvince(String province) {
        this.province = province;
    }
}
