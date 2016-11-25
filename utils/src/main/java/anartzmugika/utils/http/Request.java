/*
 * Copyright (c) 2016. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package anartzmugika.utils.http;

/**
 * Created by anartzmugika on 25/11/16.
 */

import android.net.Uri;
import android.os.StrictMode;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

/***************************************************************************************************
 * Created by Anartz on 06/10/2014.
 ***************************************************************************************************
 * Updated by Anartz on 10/03/2016
 **************************************************************************************************/
public class Request {

    private boolean https;
    public void Request() {}
    public void Request(boolean https)
    {
        this.https = https;
    }

    private void executeStrictModeThreadPolicy()
    {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }
    //request from server with POST

    /***************************************************************
     * POST PARAMETERS TO SEND...
     * ************************************************************
     */
    public String getHttpPostAPI(String request_url, Uri.Builder builder) throws IOException {


        executeStrictModeThreadPolicy();

        URL url = new URL(request_url);
        HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
        conn.setReadTimeout(20000);
        conn.setConnectTimeout(30000);
        conn.setUseCaches(true);
        conn.addRequestProperty("Cache-Control", "max-age=1800");
        conn.setRequestMethod("POST");

        conn.setDoInput(true);
        conn.setDoOutput(true);

        //Encode Query parameters....
        String query = builder.build().getEncodedQuery();

        OutputStream os = conn.getOutputStream();
        BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(os, "UTF-8"));
        writer.write(query);
        writer.flush();
        writer.close();
        os.close();

        conn.connect();

        System.out.println("Response code: " + conn.getResponseCode());
        System.out.println("Response message: " + conn.getResponseMessage());

        // read the output from the server
        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        StringBuilder stringBuilder = new StringBuilder();

        String line;
        while ((line = reader.readLine()) != null)
        {
            stringBuilder.append(line + "\n");
        }
        return stringBuilder.toString();

    }


    //Example to set auth token to get request from server
    public String getHttpGETAPI (String request_url) throws IOException {

        executeStrictModeThreadPolicy();

        URL url;
        BufferedReader reader = null;
        StringBuilder stringBuilder;


        System.out.println("Request URL: " + request_url);

        try
        {
            // create the HttpURLConnection
            url = new URL(request_url);
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();

            // just want to do an HTTP GET here
            connection.setRequestMethod("GET");

            // uncomment this if you want to write output to this url
            //connection.setDoOutput(true);
            connection.setUseCaches(true);
            connection.addRequestProperty("Cache-Control", "max-age=1800");

            connection.setConnectTimeout(20*1000);
            // give it 40 seconds to respond
            connection.setReadTimeout(40*1000);
            connection.connect();

            System.out.println("Response code: " + connection.getResponseCode());
            System.out.println("Response message: " + connection.getResponseMessage());

            // read the output from the server
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            stringBuilder = new StringBuilder();

            String line = null;
            while ((line = reader.readLine()) != null)
            {
                stringBuilder.append(line + "\n");
            }
            return stringBuilder.toString();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
        finally
        {
            // close the reader; this can throw an exception too, so
            // wrap it in another try/catch block.
            if (reader != null)
            {
                try
                {
                    reader.close();
                }
                catch (IOException ioe)
                {
                    ioe.printStackTrace();
                }
            }
        }
    }
}
