/*
 * Copyright (c) 2016. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.mugan86;

/**************************************************************************************************
 * Created by anartzmugika on 25/11/16.
 *************************************************************************************************/

public class Constants {

    //https://developers.themoviedb.org/3/getting-started/images
    //%d = width of image / poster_path value for example:
    // width (w): 500
    // poster_path: /syBA7Scz1LsY9zymB1wvR4a4RMO.jpg
    // https://image.tmdb.org/t/p/w500/syBA7Scz1LsY9zymB1wvR4a4RMO.jpg
    public static final String IMAGE_URL_BASE = "https://image.tmdb.org/t/p/w%d%s";

    public static final String IMAGE_MOVIE_DB = "https://image.tmdb.org/t/p/w320"; //

    public static final String API_KEY = "9600ef8b528a214cba2d53c6cdd71708";
}
