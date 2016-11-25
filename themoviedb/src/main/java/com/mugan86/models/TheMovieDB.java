/*
 * Copyright (c) 2016. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.mugan86.models;

import api.Request;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import constants.ConstantValues;
import constants.Urls;
import interfaces.TheMovieDBIF;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import utils.Execution;

/**
 *
 * @author Anartz Muxika
 */
public class TheMovieDB implements TheMovieDBIF{
    
    private String api_key;
    private String language;
    private boolean create_file;
    
    public TheMovieDB(String api_key, String language, boolean create_file)
    {
        setApi_key(api_key);
        setLanguage(language);
        setCreate_file(create_file);
    }
    
    public boolean isCreate_file() {
        return create_file;
    }

    public final void setCreate_file(boolean create_file) {
        this.create_file = create_file;
    }
    
    public String getApi_key() {
        return api_key;
    }

    public final void setApi_key(String api_key) {
        this.api_key = api_key;
    }
    
    public String getLanguage() {
        return language;
    }

    public final void setLanguage(String language) {
        this.language = language;
    }

    @Override
    public ArrayList<Movie> getMostPopularMovies() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Movie> getUSCertificateKindMostPopularMovies(int page_limit) {
        //http://api.themoviedb.org/3/discover/movie?primary_release_year=2011&sort_by=vote_average.desc&api_key=9600ef8b528a214cba2d53c6cdd71708
        try {
             String url = Urls.DEFAULT_DISCOVER_MOVIE + ConstantValues.API_PARAM + this.getApi_key() + "&certification_country=US&certification.lte=G&sort_by=popularity.desc&vote_count.gte=100" + ConstantValues.LANGUAGE + this.getLanguage();
            return getMovieList(url, new ArrayList<>(), 1, page_limit, ConstantValues.POPULAR_KIDS_MOVIES);
        } catch (IOException ex) {
            Logger.getLogger(TheMovieDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    
    @Override
    public ArrayList<Movie> getReleaseMoviesFromSelectYearRange(int start_year, int finish_year, int start_page, int page_limit) {
        //TODO Pending to Finish!!!
        String url;
        if (start_year < finish_year +1)
        {
            //https://api.themoviedb.org/3/discover/movie?api_key=9600ef8b528a214cba2d53c6cdd71708&primary_release_date.gte=1964-01-01&primary_release_date.lte=1964-12-31&language=es&page=2
            String filt = ConstantValues.PRIMARY_MOVIE_RELEASE_DATE_GTE + (start_year) + "-01-01" + ConstantValues.PRIMARY_MOVIE_RELEASE_DATE_LTE + (start_year) +"-12-31";
            try {
                url = Urls.DEFAULT_DISCOVER_MOVIE + ConstantValues.API_PARAM + this.getApi_key() + 
                filt + ConstantValues.LANGUAGE + this.getLanguage();
                System.out.println(url);
                getMovieList(url, new ArrayList<Movie>(), start_page, page_limit, ConstantValues.RELEASE_MOVIES+start_year);
            } catch (Exception ex) {
                Logger.getLogger(TheMovieDB.class.getName()).log(Level.SEVERE, null, ex);
            }
            start_year++;
            return getReleaseMoviesFromSelectYearRange(start_year, finish_year, start_page, page_limit);
        }
        
        return new ArrayList<>();
    }

    @Override
    public ArrayList<Movie> getMostPopularMoviesFromSelectYear(String start_year, int page_limit) {
        //http://api.themoviedb.org/3/discover/movie?primary_release_year=2011&sort_by=vote_average.desc&api_key=9600ef8b528a214cba2d53c6cdd71708
        try {
             String url = Urls.DEFAULT_DISCOVER_MOVIE + ConstantValues.API_PARAM + this.getApi_key() + ConstantValues.SORT_BY_VOTE_AVERAGE +
                ConstantValues.PRIMARY_MOVIE_RELEASE_YEAR + start_year + ConstantValues.LANGUAGE + this.getLanguage();
            return getMovieList(url, new ArrayList<>(), 1, page_limit, ConstantValues.POPULAR_MOST_MOVIES+start_year);
        } catch (IOException ex) {
            Logger.getLogger(TheMovieDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public ArrayList<TV> getTVShowReleaseFirtsAirDateBetweenDates(int start_year, int finish_year, int start_page, int page_limit, ArrayList<TV> show) {
        //https://api.themoviedb.org/3/discover/tv?api_key=9600ef8b528a214cba2d53c6cdd71708&first_air_date.lte=2007-01-01&first_air_date.gte=2006-09-09
        String url;
        if (start_year < finish_year +1)
        {
            String filt = ConstantValues.FIRST_AIR_DATE_GTE + (start_year) + "-01-01" + ConstantValues.FIRST_AIR_DATE_LTE + (start_year) +"-12-31";
            try {
                url = Urls.DEFAULT_DISCOVER_TV + ConstantValues.API_PARAM + this.getApi_key() + 
                filt + ConstantValues.LANGUAGE + this.getLanguage();
                System.out.println(url);
                show = getTVShowList(url, show, start_page, page_limit, ConstantValues.RELEASE_TV_SHOWS+start_year);
            } catch (Exception ex) {
                Logger.getLogger(TheMovieDB.class.getName()).log(Level.SEVERE, null, ex);
            }
            start_year++;
            System.out.println("Start Year: " + start_year);
            return getTVShowReleaseFirtsAirDateBetweenDates(start_year, finish_year, start_page, page_limit, show);
        }
        
        return show;
    }

    @Override
    public ArrayList<TV> getTVShowReleaseFirtsInSpecificYear(String year) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Movie> getUpcomingMovies(int page_limit) {
        try {
            String url = Urls.UPCOMING_MOVIES + ConstantValues.API_PARAM + this.getApi_key() + ConstantValues.LANGUAGE + this.getLanguage();
            return getMovieList(url, new ArrayList<>(), 1, page_limit, ConstantValues.UPCOMING_FILE);
        } catch (IOException ex) {
            Logger.getLogger(TheMovieDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    @Override
    public ArrayList<Movie> getNowPlayingMovies() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    /**
     * 
     * @param url
     * @param movies_list
     * @param page: start_page (1 minimum)
     * @param total_pages: -1 OR 1 = 1 / 0 = 1500 (UNLIMITED) / ELSE specific total_pages
     * @param type
     * @return
     * @throws IOException 
     */
    private ArrayList<Movie> getMovieList(String url, ArrayList<Movie> movies_list, int page, int total_pages, String type) throws IOException
    {
        ArrayList<Movie> new_movies = new ArrayList<>();
        
        if (total_pages == -1 || total_pages == 1) total_pages = 1;
        if (total_pages == 0) total_pages = 1500; //Unlimited
        
        if (page <= total_pages)
        {
            String data = Request.getHttpGETAPI(url+"&page="+page);
           
            System.out.println(data);
            
            if(this.isCreate_file()) createJSONFile("Downloads_Files/movie_" + type + "_" + page + ".json", data);
            
            //Add new page
            page++;

            JSONObject object = (JSONObject) new JSONObject(data);
            if (total_pages == 1500) total_pages = (Integer)object.getInt("total_pages");

            JSONArray array = (JSONArray) object.getJSONArray("results");

            if (array.length() > 0)
            {
                new_movies = (ArrayList<Movie>) fromJson(array.toString(),
                        new TypeToken<ArrayList<Movie>>() {
                        }.getType());
                movies_list = addMovies(movies_list, new_movies);
                Execution.sleepExecutionBySpecifiedDuration(3);
                return getMovieList(url, movies_list, page, total_pages, type);
            } 
        }
        return movies_list;
    }
    
     /**
     * 
     * @param url
     * @param tv_show_list
     * @param page: start_page (1 minimum)
     * @param total_pages: -1 OR 1 = 1 / 0 = 1500 (UNLIMITED) / ELSE specific total_pages
     * @param type
     * @return
     * @throws IOException 
     */
    private ArrayList<TV> getTVShowList(String url, ArrayList<TV> tv_show_list, int page, int total_pages, String type) throws IOException
    {
        ArrayList<TV> new_tv_shows = new ArrayList<>();
        
        if (total_pages == -1 || total_pages == 1) total_pages = 1;
        if (total_pages == 0) total_pages = 1500; //Unlimited
        
        if (page <= total_pages)
        {
            String data = Request.getHttpGETAPI(url+"&page="+page);
           
            System.out.println(data);
            
            if(this.isCreate_file()) createJSONFile(ConstantValues.DEFAULT_DOWNLOAD_DIRECTORY + type + "_" + page + ".json", data);
            
            //Add new page
            page++;

            JSONObject object = (JSONObject) new JSONObject(data);
            if (total_pages == 1500) total_pages = (Integer)object.getInt("total_pages");

            JSONArray array = (JSONArray) object.getJSONArray("results");

            if (array.length() > 0)
            {
                new_tv_shows = (ArrayList<TV>) fromJson(array.toString(),
                        new TypeToken<ArrayList<TV>>() {
                        }.getType());
                tv_show_list = addTvShows(tv_show_list, new_tv_shows);
                //if (page % 6 == 0) 
                    Execution.sleepExecutionBySpecifiedDuration(3);
                return getTVShowList(url, tv_show_list, page, total_pages, type);
            } 
        }
        return tv_show_list;
    }

    @Override
    public Movie getMovie(String id) {
        
        String url = Urls.URL_MOVIE + id + ConstantValues.API_PARAM + this.getApi_key() + ConstantValues.LANGUAGE + this.getLanguage();
        String credits = Urls.URL_MOVIE + id + "/credits" + ConstantValues.API_PARAM + this.getApi_key() + ConstantValues.LANGUAGE + this.getLanguage();
        System.out.println(url+"\n" +credits);
        
        try {
            String json = Request.getHttpGETAPI(url);
            String json2 = Request.getHttpGETAPI(credits);
            try
            {
                Movie movie = (Movie) fromJson(json,
                    new TypeToken<Movie>() {
                    }.getType());
            }catch(Exception e)
            {
                e.printStackTrace();
            }
            System.out.println(json);
            if(this.isCreate_file()) createJSONFile(ConstantValues.DEFAULT_DOWNLOAD_DIRECTORY + "movie_more/"+ConstantValues.MOVIE_FILE_START_NAME + id + ".json", json);
            if(this.isCreate_file()) createJSONFile(ConstantValues.DEFAULT_DOWNLOAD_DIRECTORY + "movie_credits/"+ConstantValues.MOVIE_FILE_START_NAME + id + ".json", json2);
            return new Movie();
        } catch (IOException ex) {
            Logger.getLogger(TheMovieDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
        
    }

    @Override
    public ArrayList<Genre> getGenreToTVorMovies(boolean tv) {
        //https://api.themoviedb.org/3/genre/tv/list?api_key=9600ef8b528a214cba2d53c6cdd71708
        String url, genre_tv_or_movie;
        if (tv)
        {
            url = Urls.URL_GENRE_TV;
            genre_tv_or_movie = "genre_tv_";
        }
        else 
        {
            url = Urls.URL_GENRE_MOVIES;
            genre_tv_or_movie = "genre_movies_";
        }
        
        //Add filters values, api key...
        url = url + ConstantValues.API_PARAM + this.getApi_key() + ConstantValues.LANGUAGE + this.getLanguage();
        
        System.out.println(url);
        try {
            String json = Request.getHttpGETAPI(url);
            
            JSONObject object = (JSONObject) new JSONObject(json);
            

            JSONArray array = (JSONArray) object.getJSONArray("genres");
            
            if(this.isCreate_file()) createJSONFile(ConstantValues.DEFAULT_DOWNLOAD_DIRECTORY +genre_tv_or_movie + this.getLanguage()+ ".json", json);
            
            return (ArrayList<Genre>) fromJson(array.toString(),
                    new TypeToken<ArrayList<Genre>>() {
                    }.getType());
        } catch (IOException ex) {
            System.err.println("No load correctly genre list, please try again later.");
        }
        return new ArrayList<>();
    }

    @Override
    public ArrayList<Person> getPopularPerson(int page, int total_pages) {

        if (page < total_pages +1)
        {
            //
            try {
                String json = Request.getHttpGETAPI("https://api.themoviedb.org/3/person/popular?api_key=9600ef8b528a214cba2d53c6cdd71708&language=es&page=" +page);

                JSONObject object = (JSONObject) new JSONObject(json);

                if (total_pages == 1500) total_pages = (Integer)object.getInt("total_pages");
                JSONArray array = (JSONArray) object.getJSONArray("results");

                if(this.isCreate_file()) createJSONFile(ConstantValues.DEFAULT_DOWNLOAD_DIRECTORY
                        +"popular_person_" + this.getLanguage()+"_page_" + page + ".json", json);
                        page++;

                Execution.sleepExecutionBySpecifiedDuration(2);
                getPopularPerson(page, total_pages);

            } 
            catch (IOException ex) 
            {
                System.err.println("No load correctly jobs list, please try again later.");
            }
        }
        return new ArrayList<>();
    }

    @Override
    public ArrayList<Jobs> getJobList() {
        
        ///job/list
        String url = Urls.URL_JOBS_LIST + ConstantValues.API_PARAM + this.getApi_key() + ConstantValues.LANGUAGE + this.getLanguage();
        
        System.out.println(url);
        try {
            String json = Request.getHttpGETAPI(url);
            
            JSONObject object = (JSONObject) new JSONObject(json);
            

            JSONArray array = (JSONArray) object.getJSONArray("jobs");
            
            if(this.isCreate_file()) createJSONFile(ConstantValues.DEFAULT_DOWNLOAD_DIRECTORY +"jobs_list_" + this.getLanguage()+ ".json", json);
            
            return (ArrayList<Jobs>) fromJson(array.toString(),
                    new TypeToken<ArrayList<Jobs>>() {
                    }.getType());
        } catch (IOException ex) {
            System.err.println("No load correctly jobs list, please try again later.");
        }
        return new ArrayList<>();
    }
    
    private static ArrayList<Movie> addMovies(ArrayList<Movie> movies_list, ArrayList<Movie> new_movies)
    {
        for(int i = 0; i < new_movies.size(); i++)
        {
            movies_list.add(new_movies.get(i));
        }
        return movies_list;
    }
    
    private static ArrayList<TV> addTvShows(ArrayList<TV> tv_shows_list, ArrayList<TV> new_tv_shows)
    {
        for(int i = 0; i < new_tv_shows.size(); i++)
        {
            tv_shows_list.add(new_tv_shows.get(i));
        }
        return tv_shows_list;
    }
    
    private Object fromJson(String jsonString, Type type) {
        return new Gson().fromJson(jsonString, type);
    }

    @Override
    public void createJSONFile(String path, String json) {
        try {
           
            BufferedWriter bfw = new BufferedWriter(new FileWriter(path, false));
            
            //JSON Array Start
            bfw.write("[");
            bfw.write(json);
            bfw.write("]");
            bfw.close();
           
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    @Override
    public void openDownloadDirectoryFilesAndCreateSQLLines (int year, int type)
    {
        File folder = new File(ConstantValues.DEFAULT_DOWNLOAD_DIRECTORY);
        File [] listOfFiles = folder.listFiles();
        if (year==-1) year = 0;
        String find_filter_in_file_name = "";
        if (type == 1) find_filter_in_file_name = "movie";
        if (type == 2) find_filter_in_file_name = "tv";
        if (type == 3) find_filter_in_file_name = "genre";
        if (type == 4) find_filter_in_file_name = "person";
        
        System.out.println("year: " + year);
            ArrayList<String> files_to_read = new ArrayList<>();
            for (File listOfFile : listOfFiles) {
                if (listOfFile.isFile() && !listOfFile.getName().equals(".DS_Store") && listOfFile.getName().contains(find_filter_in_file_name)) {
                    
                    //if (type == 1 && listOfFile.getName().contains(String.valueOf(year)))
                    files_to_read.add(listOfFile.getName());
                } 
            }
            
            //files_to_read.size()
            for (int i = 0; i < files_to_read.size(); i++)
            {
                System.out.println("\n*************************************************************\nFiles: " 
                                    + files_to_read.size() + " / Progress: "+ ((i*100)/files_to_read.size()) +"%\n*************************************************************");
                
                String filename = files_to_read.get(i);
                //Leemos el fichero y lo mostramos por pantalla
                try (FileReader fr = new FileReader(ConstantValues.DEFAULT_DOWNLOAD_DIRECTORY+filename)) {
                    //Leemos el fichero y lo mostramos por pantalla
                    int valor=fr.read();
                    String text = "";
                    while(valor!=-1){
                        //System.out.print((char)valor);
                        valor=fr.read();
                        text = text + (char)valor;

                    }   


                    JSONObject object = (JSONObject) new JSONObject(text.substring(0,text.length()-1));


                    JSONArray array = (JSONArray) object.getJSONArray("results");


                    if (type == 1) //Movie
                    {
                        ArrayList<Movie> objects = (ArrayList<Movie>) fromJson(array.toString(),
                                    new TypeToken<ArrayList<Movie>>() {}.getType());

                        //System.out.println(objects.size());
                        for (int h = 0; h < objects.size(); h++)
                        {
                            Movie movie = objects.get(h);
                            if (movie.getGenres_ids().size() < 6)
                            {
                                while (movie.getGenres_ids().size() < 6)
                                {
                                    movie.setGenres_ids("");
                                }
                            }
                            String sql = ConstantValues.INSERT_MOVIE_DB_SENTENCE + " (" 
                                                            + movie.getId() + ", '" + movie.getTitle() + "', '" 
                                                            + movie.getOriginal_title()+"', '"+ movie.getOriginal_language() + "', '"
                                                            + movie.getPoster_path()+"', '"+ movie.getGenres_ids().get(0) + "', '"
                                                            + movie.getGenres_ids().get(1)+"', '"+ movie.getGenres_ids().get(2) + "','"
                                                            + movie.getGenres_ids().get(3)+"', '"+ movie.getGenres_ids().get(4) + "', '"
                                                            + movie.getGenres_ids().get(5)+"','', 0, '"+ movie.getRelease_date() + "',0 , '',"
                                                            + movie.getVote_count() + ", '" + String.valueOf(movie.getVote_average()) + "');";

                            saveInFile(sql, "movies_"+year+".sql");
                            //System.out.println("INSERT IGNORE INTO genre VALUES (" + objects.get(h).getId() + ", '" + objects.get(h).getName() + "');");

                        }
                    }
                    else if (type == 2) //TV
                    {
                        ArrayList<TV> objects = (ArrayList<TV>) fromJson(array.toString(),
                                    new TypeToken<ArrayList<TV>>() {}.getType());

                        //System.out.println(objects.size());
                        for (int h = 0; h < objects.size(); h++)
                        {
                            TV movie = objects.get(h);
                            if (movie.getGenre_ids().size() < 6)
                            {
                                while (movie.getGenre_ids().size() < 6)
                                {
                                    movie.setGenre_ids(new Genre());
                                }
                            }
                            
                            
                            String sql = "";/*ConstantValues.INSERT_TV_DB_SENTENCE + " (" 
                                                            + movie.getId() + ", '" + movie.getName() + "', '" 
                                                            + movie.getOriginal_name()+"', '"+ movie.getOriginal_language() + "', '"
                                                            + movie.getPoster_path()+"', '"+ movie.getGenre_ids().get(0) + "', '"
                                                            + movie.getGenre_ids().get(1)+"', '"+ movie.getGenre_ids().get(2) + "','"
                                                            + movie.getGenre_ids().get(3)+"', '"+ movie.getGenre_ids().get(4) + "', '"
                                                            + movie.getGenre_ids().get(5)+"','" + "', 0, '"+ movie.getRelease_date() + "',0 , '',"
                                                            + movie.getVote_count() + ", '" + String.valueOf(movie.getVote_average()) + "');";*/
                            
                                //('11', '22222', '2222', '2222', '222', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '');


                            saveInFile(sql, "series_"+year+".sql");
                            //System.out.println("INSERT IGNORE INTO genre VALUES (" + objects.get(h).getId() + ", '" + objects.get(h).getName() + "');");

                        }
                    }
                    else if (type == 4) //Person
                    {
                        
                        ArrayList<Person> objects = (ArrayList<Person>) fromJson(array.toString(),
                                    new TypeToken<ArrayList<Person>>() {}.getType());
                        
                        //Loop and inside get more info before create sql
                        
                        for (int h = 0; h < objects.size(); h++)
                        {
                            
                            Person person = objects.get(h);
                            
                            person = getPerson(String.valueOf(person.getId()));
                            
                            person.getBirthday();
                            
                            String sql = ConstantValues.INSERT_PERSON_DB_SENTENCE + " (" 
                                                            + person.getId() + ", '" + person.getImdb_id() + "', '" 
                                                            + person.getName()+"', '"+ person.getPlace_of_birth() + "', '"
                                                            + person.getProfile_path()+"', '', '"
                                                            + "', '"+ person.getHomepage() + "','"
                                                            + person.getBirthday()+"', '"+ person.getDeathday() + "', "
                                                            + person.getGender()+"," + person.getPopularity() + ", '', '');";
                            
                            System.out.println(sql);
                            saveInFile(sql, "persons.sql");
                            if (h % 6 == 0) Execution.sleepExecutionBySpecifiedDuration(2);

                            //saveInFile(sql, "movies_"+year+".sql");
                            //System.out.println("INSERT IGNORE INTO genre VALUES (" + objects.get(h).getId() + ", '" + objects.get(h).getName() + "');");

                        }
                    }
                    
                    

                    //Cerramos el stream
                } catch (IOException ex) {
                    Logger.getLogger(TheMovieDB.class.getName()).log(Level.SEVERE, null, ex);
                    System.out.println("Salbuespena (485)");
                }
            }
        
    }
    
    public Person getPerson(String id) {
        String url = Urls.URL_PERSON + id + ConstantValues.API_PARAM + this.getApi_key() + ConstantValues.LANGUAGE + this.getLanguage();
        
        System.out.println(url);
        
        try {
            String json = Request.getHttpGETAPI(url);
            if(this.isCreate_file()) createJSONFile(ConstantValues.DEFAULT_DOWNLOAD_DIRECTORY + "persons_id/"+ ConstantValues.PERSON_FILE_NAME + id + ".json", json);
            return (Person) fromJson(json,
                    new TypeToken<Person>() {
                    }.getType());
        } catch (IOException ex) {
            Logger.getLogger(TheMovieDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;    
    }
    
    private void saveInFile(String sql, String file_name_with_extension)
    {
        BufferedWriter bfw;
        try {
            bfw = new BufferedWriter(new FileWriter(file_name_with_extension, true));
            //JSON Array Start
            bfw.write(sql);
            bfw.newLine();
            bfw.close();
        } catch (IOException ex) {
            Logger.getLogger(TheMovieDB.class.getName()).log(Level.SEVERE, null, ex);
        }
             
    }
    
    public void openSeriesFilesAndDowloadMoreInfoAndCredits()
    {
        File folder = new File(ConstantValues.DEFAULT_DOWNLOAD_DIRECTORY);
        File [] listOfFiles = folder.listFiles();
        ArrayList<String> files_to_read = new ArrayList<>();
        for (File listOfFile : listOfFiles) {
            if (listOfFile.isFile() && !listOfFile.getName().equals(".DS_Store")) {

                //if (type == 1 && listOfFile.getName().contains(String.valueOf(year)))
                files_to_read.add(listOfFile.getName());
            } 
        }
        
        System.out.println(files_to_read.size());
        
        for (int i = 0; i < files_to_read.size(); i++)
        {
            System.out.println("\n*************************************************************\nFiles: " 
                                    + files_to_read.size() + " / Progress: "+ ((i*100)/files_to_read.size()) +"%\n*************************************************************");
            String filename = files_to_read.get(i);
                //Leemos el fichero y lo mostramos por pantalla
                try (FileReader fr = new FileReader(ConstantValues.DEFAULT_DOWNLOAD_DIRECTORY+filename)) {
                    //Leemos el fichero y lo mostramos por pantalla
                    int valor=fr.read();
                    String text = "";
                    while(valor!=-1){
                        //System.out.print((char)valor);
                        valor=fr.read();
                        text = text + (char)valor;

                    }   
                    
                    System.out.println(text.substring(0,text.length()-1));


                    JSONObject object = (JSONObject) new JSONObject(text.substring(0,text.length()-1));


                    JSONArray array = (JSONArray) object.getJSONArray("results");
                    ArrayList<TV> objects = (ArrayList<TV>) fromJson(array.toString(),
                                    new TypeToken<ArrayList<TV>>() {}.getType());
                    
                    System.out.println("Objects: " + objects.size());
                    for (int h = 0; h < objects.size(); h++)
                    {
                        TV tv = objects.get(h);
                        System.out.println("****************************CREATE TV: " + tv.getName() + " *****************************");
                        

                        tv = getTV(String.valueOf(tv.getId()));

                        

                        
                        if (h % 6 == 0) Execution.sleepExecutionBySpecifiedDuration(2);

                        //saveInFile(sql, "movies_"+year+".sql");
                        //System.out.println("INSERT IGNORE INTO genre VALUES (" + objects.get(h).getId() + ", '" + objects.get(h).getName() + "');");

                    }
                    

                    
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }
        }
    }
    
    public TV getTV(String id) {
        
        String url = Urls.URL_TV + id + ConstantValues.API_PARAM + this.getApi_key() + ConstantValues.LANGUAGE + this.getLanguage();
        String credits = Urls.URL_TV + id + "/credits" + ConstantValues.API_PARAM + this.getApi_key() + ConstantValues.LANGUAGE + this.getLanguage();
        
        System.out.println(url);
        System.out.println(credits);
        
        try {
            String json = Request.getHttpGETAPI(url);
            String json2 = Request.getHttpGETAPI(credits);
            if(this.isCreate_file()) createJSONFile(ConstantValues.DEFAULT_DOWNLOAD_DIRECTORY + "more_info_tv/" + ConstantValues.TV_FILE_START_NAME + ConstantValues.MORE_INFO+ id + ".json", json);
            if(this.isCreate_file()) createJSONFile(ConstantValues.DEFAULT_DOWNLOAD_DIRECTORY + "credits_tv/" + ConstantValues.TV_FILE_START_NAME + ConstantValues.CREDITS+ id + ".json", json2);
            return (TV) fromJson(json,
                    new TypeToken<TV>() {
                    }.getType());
        } catch (IOException ex) {
            Logger.getLogger(TheMovieDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
        
    }
    
    
    
    @Override
    public void openSeriesMoreInfoAndCredits(int io)
    {
        String directory;
        if (io==-2)
        {
            directory = ConstantValues.DEFAULT_DOWNLOAD_DIRECTORY+"credits_tv/";
        }
        else
        {
            directory = ConstantValues.DEFAULT_DOWNLOAD_DIRECTORY+"more_info_tv/";
        }
        File folder = new File(directory);
        File [] listOfFiles = folder.listFiles();
        System.out.println("listOfFiles: " + listOfFiles.length);
        ArrayList<String> files_to_read = new ArrayList<>();
        int count = 0;
        for (File listOfFile : listOfFiles) {
            
            if (listOfFile.isFile() && !listOfFile.getName().equals(".DS_Store")) {

                //if (type == 1 && listOfFile.getName().contains(String.valueOf(year)))
                files_to_read.add(listOfFile.getName());
                count++;
            } 
        }
        
        System.out.println(files_to_read.size()+"*******"+ count);
        int file_number = 0;
        ArrayList<TV> tvs = new ArrayList<>();
        //more_info_tv/
        for (int i = 0; i < files_to_read.size(); i++)
        {
            System.out.println("\n*************************************************************\nFiles: " 
                                    + files_to_read.size() + " / Progress: "+ ((i*100)/files_to_read.size()) +"%\n*************************************************************");
            String filename = files_to_read.get(i);
                //Leemos el fichero y lo mostramos por pantalla
                try (FileReader fr = new FileReader(directory+filename)) {
                    //Leemos el fichero y lo mostramos por pantalla
                    int valor=fr.read();
                    String text = "";
                    while(valor!=-1){
                        //System.out.print((char)valor);
                        valor=fr.read();
                        text = text + (char)valor;

                    }   
                    
                    //System.out.println(text.substring(0,text.length()-1));


                    JSONObject object = (JSONObject) new JSONObject(text.substring(0,text.length()-1));


                    //JSONArray array = (JSONArray) object.getJSONArray("results");
                    
                    if (i % 3000 == 0 && i > 10) file_number++;
                    
                    if (io != -2)
                    {
                        tvs.add((TV) fromJson(object.toString(),
                                    new TypeToken<TV>() {}.getType()));
                        TV tv = tvs.get(i);


                        if (tv.getGenre_ids() == null || tv.getGenre_ids().size() < 6)
                        {

                            while (tv.getGenre_ids().size() < 6)
                            {
                                tv.setGenre_ids(new Genre());
                            }
                        }
                        final Gson gson = new Gson();

                        String created_by_object = gson.toJson(tv.getCreated_by());

                        if (created_by_object == null)
                        {
                            created_by_object = "";
                        }
                   
                        //created_by_object

                        String genre_object = gson.toJson(tv.getEpisode_run_time());

                        if (genre_object == null)
                        {
                            genre_object = "";
                        }
 
                        String sql = ConstantValues.INSERT_TV_DB_SENTENCE + 
                           + tv.getId() + ", '" + tv.getName() + "', '" 
                                                            + tv.getOriginal_name()+"', '"+ tv.getOriginal_language() + "', '"
                                                            + tv.getPoster_path()+"', '"+ tv.getGenre_ids().get(0).getId() + "', '"
                                                            + tv.getGenre_ids().get(1).getId()+"', '"+ tv.getGenre_ids().get(2).getId() + "','"
                                                            + tv.getGenre_ids().get(3).getId()+"', '"+ tv.getGenre_ids().get(4).getId() + "', '"
                                                            + tv.getGenre_ids().get(5).getId()+"','" + tv.getFirst_air_date()+ "','" 
                                                            + tv.getLast_air_date() + "','" + genre_object + "', '"
                                                            + tv.getHomepage() + "', '" + tv.getStatus() + "', "
                                                            + tv.getNumber_of_episodes() + ", " + tv.getNumber_of_seasons() + ", "
                                                            + tv.getVote_count() + ", " + tv.getVote_average() + ", '', '', '', '', '', '', '" + created_by_object + "');";
                        System.out.println(sql);
                   
                        
                   
                        saveInFile(sql, ConstantValues.DEFAULT_DOWNLOAD_DIRECTORY+"tv_more.json_" + file_number + ".sql");
                    }
                    else
                    {
                        Integer id = (Integer) object.getInt("id");
                        JSONArray array = (JSONArray) object.getJSONArray("cast");
                        System.out.println(array.toString());
                        
                        String sql = "UPDATE tv SET actor_1 = '" +(array.toString()).replace("'", "´") + "' WHERE id=" + id+";";
                        
                        saveInFile(sql, ConstantValues.DEFAULT_DOWNLOAD_DIRECTORY+"cast_" + file_number + ".sql");
                    }
                   
                    
                    /*ArrayList<TV> objects = (ArrayList<TV>) fromJson(array.toString(),
                                    new TypeToken<ArrayList<TV>>() {}.getType());
                    
                    System.out.println("Objects: " + objects.size());
                    for (int h = 0; h < objects.size(); h++)
                    {
                        TV tv = objects.get(h);
                        System.out.println("****************************CREATE TV: " + tv.getName() + " *****************************");
                        

                        tv = getTV(String.valueOf(tv.getId()));

                        

                        
                        if (h % 6 == 0) Execution.sleepExecutionBySpecifiedDuration(2);

                        //saveInFile(sql, "movies_"+year+".sql");
                        //System.out.println("INSERT IGNORE INTO genre VALUES (" + objects.get(h).getId() + ", '" + objects.get(h).getName() + "');");

                    }*/
                    

                    
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }
        }
        
        System.out.println(tvs.size());
    }

    
    
    
}
