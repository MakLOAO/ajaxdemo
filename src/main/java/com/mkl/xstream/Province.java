package com.mkl.xstream;

import java.util.ArrayList;
import java.util.List;

public class Province {

    private String name;

    private List<City> cities = new ArrayList<>();

    public Province() {}

    public Province(String name, List<City> cities) {
        this.name = name;
        this.cities = cities;
    }

    @Override
    public String toString() {
        return "Province{" +
                "name='" + name + '\'' +
                ", cities=" + cities +
                '}';
    }

    public void addCity(City city) {
        cities.add(city);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<City> getCities() {
        return cities;
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
    }
}
