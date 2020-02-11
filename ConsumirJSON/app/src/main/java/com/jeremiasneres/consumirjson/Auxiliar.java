package com.jeremiasneres.consumirjson;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Auxiliar {

    public String consumir(String reqURL){
        String response = null;
        try {
            URL url = new URL(reqURL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            InputStream is = new BufferedInputStream(connection.getInputStream());
            response = converter(is);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    private String converter(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line = null;
        try {
            while ((line =  reader.readLine()) != null){
                sb.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
}
