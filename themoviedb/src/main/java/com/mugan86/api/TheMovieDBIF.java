/*
 * Copyright (c) 2016. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.mugan86.api;

/**************************************************************************************************
 * Created by anartzmugika on 25/11/16.
 **************************************************************************************************/

import com.mugan86.models.Genre;
import com.mugan86.models.Jobs;
import com.mugan86.models.Movie;
import com.mugan86.models.Person;
import com.mugan86.models.TV;

import java.util.ArrayList;


/**
 * About API documentation INFO: https://developers.themoviedb.org/3/getting-started/introduction
 * @author Anartz Muxika
 *
 * https://api.themoviedb.org/3/person/popular?api_key=9600ef8b528a214cba2d53c6cdd71708
 * https://api.themoviedb.org/3/movie/upcoming?api_key=9600ef8b528a214cba2d53c6cdd71708
 * https://api.themoviedb.org/3/movie/now_playing?api_key=9600ef8b528a214cba2d53c6cdd71708
 * https://api.themoviedb.org/3/movie/43074?api_key=9600ef8b528a214cba2d53c6cdd71708
 * https://api.themoviedb.org/3/discover/tv?api_key=9600ef8b528a214cba2d53c6cdd71708
 * https://api.themoviedb.org/3/discover/tv?api_key=9600ef8b528a214cba2d53c6cdd71708&first_air_date_year=2010
 * https://api.themoviedb.org/3/discover/tv?api_key=9600ef8b528a214cba2d53c6cdd71708&first_air_date.lte=2007-01-01&first_air_date.gte=2006-09-09
 */
public interface TheMovieDBIF {
    /**
     * /discover/movie?sort_by=popularity.desc&vote_count.gte=200
     * @param vote_count: If number positive check greater than else less than. For example: -100 -> vote_count.lte / 100 -> vote_count.gte
     * @param page_limit -1 OR 1 = 1 / 0 = 1500 (UNLIMITED) / total_pages > 1 => specific total_pages
     * @return Movies List
     */
    public ArrayList<Movie> getMostPopularMovies(int vote_count, int page_limit);

    /**
     * US Most Kind Popular Movies and with 100 vote_count
     * /discover/movie?certification_country=US&certification.lte=G&sort_by=popularity.desc&vote_count.gte=100
     * @param page_limit -1 OR 1 = 1 / 0 = 1500 (UNLIMITED) / total_pages > 1 => specific total_pages
     * @return Movies List
     */
    public ArrayList<Movie> getUSCertificateKindMostPopularMovies(int page_limit);

    /**
     * Get most popular movies during specific year range
     * /discover/movie?primary_release_date.gte={{start_year}}-01-01&primary_release_date.lte={{start_year}}-12-31&language=es
     * @param start_year: Get movies start year
     * @param finish_year Get movies finish year (include this year)
     * @param start_page: Minimun specific start_page
     * @param page_limit: -1 OR 1 = 1 / 0 = 1500 (UNLIMITED) / total_pages > 1 => specific total_pages
     * @return Movies List
     */

    public ArrayList<Movie> getReleaseMoviesFromSelectYearRange(int start_year, int finish_year, int start_page, int page_limit, ArrayList<Movie> movie);

    /**
     * /discover/movie?primary_release_year=2010&sort_by=vote_average.desc&vote_count.gte=100
     * @param start_year: select first time release year
     * @param page_limit: Page limit to show. If value -1 only parse 1 page (convert page_limit = 2)
     * @return Movies List
     */
    public ArrayList<Movie> getMostPopularMoviesFromSelectYear(String start_year, int page_limit);

    /**
     * Show tv shows to release first in especific date range
     * /discover/tv?first_air_date.lte=second_data&first_air_date.gte=first_data
     * @param start_year Min 1901 year. If less than 1901, not return result
     * @param finish_year Min 1901 year. If less than 1901, not return result
     * @param start_page Min 1901 year. If less than 1901, not return result
     * @param page_limit
     * @return TV Shows List
     */
    public ArrayList<TV> getTVShowReleaseFirtsAirDateBetweenDates(int start_year, int finish_year, int start_page, int page_limit, ArrayList<TV> show);

    /**
     * /discover/tv?first_air_date_year=year
     *  @param year
     * @param start_page
     * @param page_limit
     * @return TV Shows List
     */
    public ArrayList<TV> getTVShowReleaseFirtsInSpecificYear(int year, int start_page, int page_limit);

    /**
     * Next month premiere movies return
     * /movie/upcoming
     * @param page_limit: Page limit to show. If value -1 only parse 1 page (convert page_limit = 2)
     * @return
     */

    public ArrayList<Movie> getUpcomingMovies(int page_limit);

    /**
     * From a month earlier to a next week from the current date
     * /movie/now_playing
     * @return
     */

    public ArrayList<Movie> getNowPlayingMovies();

    /**
     * Select movie info
     * /movie/id
     * @param id: Movie ID
     * @return
     */

    public Movie getMovie(String id);

    /**
     * /genre/tv/list
     * @param tv: Get genres to TV (false return movies)
     * @return
     */
    public ArrayList<Genre> getGenreToTVorMovies(boolean tv);

    /**
     * /person/popular
     * @param page
     * @param total_pages
     * @return
     */
    public ArrayList<Person> getPopularPerson(int page, int total_pages);

    /**
     *
     * @param id
     * @return
     */
    public Person getPerson(String id);

    /**
     *
     * @param id
     * @return
     */
    public TV getTV(String id);

    /**
     * Get all list jobs in movies or/and tv with departments
     * /job/list
     * @return
     */

    public ArrayList<Jobs> getJobList();

    /**
     * Create JSON file in set path (default root directory) with specific data in String format
     * @param path: Create file directory
     * @param json: JSON File content
     */

    public void createJSONFile(String path, String json);

    /**
     *
     * @param year
     * @param type: 1: Movies / 2: TV / 3: Genres / 4: Person
     */
    public void openDownloadDirectoryFilesAndCreateSQLLines (int year, int type);

    /**
     *
     * @param io
     */
    public void openSeriesMoreInfoAndCredits(int io);


}
