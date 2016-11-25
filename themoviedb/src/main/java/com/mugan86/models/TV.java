/*
 * Copyright (c) 2016. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.mugan86.models;

import java.util.ArrayList;
import utils.AsciiUtils;

/**
 *
 * @author anartzmugika
 */
public class TV {
    private String poster_path, backdrop_path, overview,first_air_date;
    private float vote_average;
    private int id;
    private String original_language;
    private int vote_count;
    private String name;
    private String original_name;
    private String homepage;
    private ArrayList<String> origin_country;
    private ArrayList<Integer> episode_run_time;
    private String status;
    private float popularity;
    private ArrayList<Person> created_by;
    private String last_air_date;
    private ArrayList<Genre> genres;
    private int number_of_episodes, number_of_seasons;

    public int getNumber_of_episodes() {
        return number_of_episodes;
    }

    public void setNumber_of_episodes(int number_of_episodes) {
        this.number_of_episodes = number_of_episodes;
    }

    public int getNumber_of_seasons() {
        return number_of_seasons;
    }

    public void setNumber_of_seasons(int number_of_seasons) {
        this.number_of_seasons = number_of_seasons;
    }

    public ArrayList<Genre> getGenres() {
        return genres;
    }

    public void setGenres(ArrayList<Genre> genres) {
        this.genres = genres;
    }

    public String getLast_air_date() {
        return last_air_date;
    }

    public void setLast_air_date(String last_air_date) {
        this.last_air_date = last_air_date;
    }

    public ArrayList<Person> getCreated_by() {
        return created_by;
    }

    public void setCreated_by(ArrayList<Person> created_by) {
        this.created_by = created_by;
    }

    public float getPopularity() {
        return popularity;
    }

    public void setPopularity(float popularity) {
        this.popularity = popularity;
    }
    
    
    public float getVote_average() {
        return vote_average;
    }

    public void setVote_average(float vote_average) {
        this.vote_average = vote_average;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    private ArrayList<Season> seasons;

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public ArrayList<Season> getSeasons() {
        return seasons;
    }

    public void setSeasons(ArrayList<Season> seasons) {
        this.seasons = seasons;
    }

    public ArrayList<Integer> getEpisode_run_time() {
        return episode_run_time;
    }

    public void setEpisode_run_time(ArrayList<Integer> episode_run_time) {
        this.episode_run_time = episode_run_time;
    }

    public ArrayList<Genre> getGenre_ids() {
        if (this.genres == null) return new ArrayList<>();
        return genres;
    }

    public void setGenre_ids(Genre genre) {
        if (this.genres == null) this.genres = new ArrayList<>();
        this.genres.add(genre);
    }

    public ArrayList<String> getOrigin_country() {
        return origin_country;
    }

    public void setOrigin_country(ArrayList<String> origin_country) {
        this.origin_country = origin_country;
    }

    public String getPoster_path() {
        if (poster_path == null) return "";
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getBackdrop_path() {
        if (backdrop_path == null) return "";
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public String getOverview() {
        if (overview == null) return "";
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getFirst_air_date() {
        if (first_air_date == null) return "";
        return first_air_date;
    }

    public void setFirst_air_date(String first_air_date) {
        this.first_air_date = first_air_date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOriginal_language() {
        if (original_language == null) return "en";
        return original_language;
    }

    public void setOriginal_language(String original_language) {
        this.original_language = original_language;
    }

    public int getVote_count() {
        return vote_count;
    }

    public void setVote_count(int vote_count) {
        this.vote_count = vote_count;
    }

    public String getName() {
        if (name == null) name = "";
        
        return AsciiUtils.convertNonAscii(name).replace("'", "´");
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOriginal_name() {
        if (original_name == null) original_name = "";
        
        return AsciiUtils.convertNonAscii(original_name).replace("'", "´");
    }

    public void setOriginal_name(String original_name) {
        this.original_name = original_name;
    }
    
    
    
}
