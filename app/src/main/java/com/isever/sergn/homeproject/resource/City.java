package com.isever.sergn.homeproject.resource;

public class City {

    private String region; // область
    private String city;// город
    private int gerb; // ссылка на изображение герба

    public City(String region, String city, int gerb) {
        this.setRegion(region);
        this.setCity(city);
        this.setGerb(gerb);
    }

    public String getRegion() {
        return this.region;
    }

    private void setRegion(String name) {
        this.region = name;
    }

    public String getCity() {
        return this.city;
    }

    private void setCity(String capital) {
        this.city = capital;
    }

    public int getGerb() {
        return this.gerb;
    }


    public void setGerb(int gerb) {
        this.gerb = gerb;
    }
}