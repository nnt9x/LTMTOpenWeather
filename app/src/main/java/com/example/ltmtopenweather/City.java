package com.example.ltmtopenweather;

public class City {
    private String name;
    private String iconWeather;
    private String description;

    public City(){

    }

    public City(String name, String iconWeather, String description) {
        this.name = name;
        this.iconWeather = iconWeather;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIconWeather() {
        return iconWeather;
    }

    public void setIconWeather(String iconWeather) {
        this.iconWeather = iconWeather;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "City{" +
                "name='" + name + '\'' +
                ", iconWeather='" + iconWeather + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
