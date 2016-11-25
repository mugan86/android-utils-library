/*
 * Copyright (c) 2016. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mugan86.models;


import com.mugan86.Constants;
import com.mugan86.utils.AsciiUtils;

/**
 *
 * @author anartzmugika
 */
public class Person {
    private String birthday, deathday, name, homepage, imdb_id, profile_path, place_of_birth;
    private int id, gender;
    private float popularity;
    
     public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getDeathday() {
        return deathday;
    }

    public void setDeathday(String deathday) {
        this.deathday = deathday;
    }

    public String getName() {
        if (name == null) name = "";
        return AsciiUtils.convertNonAscii(name).replace("'", "´");
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public String getImdb_id() {
        return imdb_id;
    }

    public void setImdb_id(String imdb_id) {
        this.imdb_id = imdb_id;
    }

    public String getProfile_path() {
        //first parameter width and second: profile path (get from API)
        return String.format(Constants.IMAGE_URL_BASE, 500,profile_path);
    }

    public void setProfile_path(String profile_path) {
        this.profile_path = profile_path;
    }

    public String getPlace_of_birth() {
        if (place_of_birth == null) place_of_birth = "";
        return AsciiUtils.convertNonAscii(place_of_birth).replace("'", "´");
    }

    public void setPlace_of_birth(String place_of_birth) {
        this.place_of_birth = place_of_birth;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getPopularity() {
        return popularity;
    }

    public void setPopularity(float popularity) {
        this.popularity = popularity;
    }
}
