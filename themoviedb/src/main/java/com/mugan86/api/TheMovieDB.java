/*
 * Copyright (c) 2016. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.mugan86.api;

import com.mugan86.models.Genre;
import com.mugan86.models.Jobs;
import com.mugan86.models.Movie;
import com.mugan86.models.Person;
import com.mugan86.models.TV;

import java.util.ArrayList;

public class TheMovieDB implements TheMovieDBIF{
    /**
     * /discover/movie?sort_by=popularity.desc&vote_count.gte=200
     *
     * @return
     */
    @Override
    public ArrayList<Movie> getMostPopularMovies() {
        return null;
    }

    /**
     * US Most Kind Popular Movies and with 100 vote_count
     * /discover/movie?certification_country=US&certification.lte=G&sort_by=popularity.desc&vote_count.gte=100
     *
     * @param page_limit -1 OR 1 = 1 / 0 = 1500 (UNLIMITED) / total_pages > 1 => specific total_pages
     * @return
     */
    @Override
    public ArrayList<Movie> getUSCertificateKindMostPopularMovies(int page_limit) {
        return null;
    }

    /**
     * Get most popular movies during specific year range
     * /discover/movie?primary_release_date.gte={{start_year}}-01-01&primary_release_date.lte={{start_year}}-12-31&language=es
     *
     * @param start_year  : Get movies start year
     * @param finish_year Get movies finish year (include this year)
     * @param start_page  : Minimun specific start_page
     * @param page_limit  : -1 OR 1 = 1 / 0 = 1500 (UNLIMITED) / total_pages > 1 => specific total_pages
     * @return
     */
    @Override
    public ArrayList<Movie> getReleaseMoviesFromSelectYearRange(int start_year, int finish_year, int start_page, int page_limit) {
        return null;
    }

    /**
     * /discover/movie?primary_release_year=2010&sort_by=vote_average.desc&vote_count.gte=100
     *
     * @param start_year
     * @param page_limit : Page limit to show. If value -1 only parse 1 page (convert page_limit = 2)
     * @return
     */
    @Override
    public ArrayList<Movie> getMostPopularMoviesFromSelectYear(String start_year, int page_limit) {
        return null;
    }

    /**
     * Show tv shows to release first in especific date range
     * /discover/tv?first_air_date.lte=second_data&first_air_date.gte=first_data
     *
     * @param start_year
     * @param finish_year
     * @param start_page
     * @param page_limit
     * @param show
     * @return
     */
    @Override
    public ArrayList<TV> getTVShowReleaseFirtsAirDateBetweenDates(int start_year, int finish_year, int start_page, int page_limit, ArrayList<TV> show) {
        return null;
    }

    /**
     * /discover/tv?first_air_date_year=year
     *
     * @param year
     * @return
     */
    @Override
    public ArrayList<TV> getTVShowReleaseFirtsInSpecificYear(String year) {
        return null;
    }

    /**
     * Next month premiere movies return
     * /movie/upcoming
     *
     * @param page_limit : Page limit to show. If value -1 only parse 1 page (convert page_limit = 2)
     * @return
     */
    @Override
    public ArrayList<Movie> getUpcomingMovies(int page_limit) {
        return null;
    }

    /**
     * From a month earlier to a next week from the current date
     * /movie/now_playing
     *
     * @return
     */
    @Override
    public ArrayList<Movie> getNowPlayingMovies() {
        return null;
    }

    /**
     * Select movie info
     * /movie/id
     *
     * @param id : Movie ID
     * @return
     */
    @Override
    public Movie getMovie(String id) {
        return null;
    }

    /**
     * /genre/tv/list
     *
     * @param tv : Get genres to TV (false return movies)
     * @return
     */
    @Override
    public ArrayList<Genre> getGenreToTVorMovies(boolean tv) {
        return null;
    }

    /**
     * /person/popular
     *
     * @param page
     * @param total_pages
     * @return
     */
    @Override
    public ArrayList<Person> getPopularPerson(int page, int total_pages) {
        return null;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Person getPerson(String id) {
        return null;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public TV getTV(String id) {
        return null;
    }

    /**
     * Get all list jobs in movies or/and tv with departments
     * /job/list
     *
     * @return
     */
    @Override
    public ArrayList<Jobs> getJobList() {
        return null;
    }

    /**
     * Create JSON file in set path (default root directory) with specific data in String format
     *
     * @param path : Create file directory
     * @param json : JSON File content
     */
    @Override
    public void createJSONFile(String path, String json) {

    }

    /**
     * @param year
     * @param type : 1: Movies / 2: TV / 3: Genres / 4: Person
     */
    @Override
    public void openDownloadDirectoryFilesAndCreateSQLLines(int year, int type) {

    }

    @Override
    public void openSeriesMoreInfoAndCredits(int io) {

    }
}
