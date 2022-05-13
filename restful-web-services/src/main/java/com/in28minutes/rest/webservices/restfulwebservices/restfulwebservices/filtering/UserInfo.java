package com.in28minutes.rest.webservices.restfulwebservices.restfulwebservices.filtering;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;

@JsonFilter("SomeBeanFilter") //For Dynamic filtering
public class UserInfo {

    private String favFood;
    private String favBrand;

    //@JsonIgnore //This is called static filtering
    private String brandIncome;

    public UserInfo(String favFood, String favBrand, String brandIncome) {
        this.favFood = favFood;
        this.favBrand = favBrand;
        this.brandIncome = brandIncome;
    }

    public String getFavFood() {
        return favFood;
    }

    public void setFavFood(String favFood) {
        this.favFood = favFood;
    }

    public String getFavBrand() {
        return favBrand;
    }

    public void setFavBrand(String favBrand) {
        this.favBrand = favBrand;
    }

    public String getBrandIncome() {
        return brandIncome;
    }

    public void setBrandIncome(String brandIncome) {
        this.brandIncome = brandIncome;
    }
}
