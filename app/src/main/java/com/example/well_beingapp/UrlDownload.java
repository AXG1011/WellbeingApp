package com.example.well_beingapp;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author Arjun Gill
 * @version 1.0
 * @since 4/9/20
 *
 * The 'UrlDownload' class reads the URL using the HTTPURLConnection object and file handling methods
 * such as input stream and Buffered Reader. This class was adapted from the 'DownloadURL.java' class
 * by Priyanka Pakhale (https://github.com/priyankapakhale/GoogleMapsNearbyPlacesDemo).
 */

public class UrlDownload {

    // The 'readUrl' method reads the incoming url using the java.net.HttpURLConnection import
    public String readUrl(String myUrl) throws IOException {

        String data = "";
        InputStream inputStream = null;
        HttpURLConnection urlConnection = null;

        try {
            URL url = new URL(myUrl);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.connect();

            inputStream = urlConnection.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            StringBuffer sb = new StringBuffer();

            String line = "";
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

            data = sb.toString();
            br.close();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            inputStream.close();
            urlConnection.disconnect();
        }
        Log.d("DownloadURL", "Returning data= " + data);
        return data;
    }

}