package com.heung.forme.custom;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class APIManager {
    static final String URL = "";

    public static Map<String, String> getCustomerRecommendation(String customerId) throws Exception {
        StringBuilder result = new StringBuilder();
        URL url = new URL(URL);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        InputStream in = new BufferedInputStream(con.getInputStream());

        BufferedReader reader = new BufferedReader(new InputStreamReader(in));

        String line;
        while ((line = reader.readLine()) != null) {
            result.append(line);
        }
        JSONObject jsonObject = new JSONObject(String.valueOf(result));
        jsonObject.get("creditCard");
        jsonObject.get("bankAccount");
        Map<String, String> recMap = new HashMap<>();

        recMap.put("creditCard", jsonObject.getString("creditCard"));
        recMap.put("bankAccount", jsonObject.getString("bankAccount"));

        return recMap;
    }

    public static Map<String, String> getClosestBranch(String customerId) throws Exception {
        StringBuilder result = new StringBuilder();
        URL url = new URL(URL);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        InputStream in = new BufferedInputStream(con.getInputStream());

        BufferedReader reader = new BufferedReader(new InputStreamReader(in));

        String line;
        while ((line = reader.readLine()) != null) {
            result.append(line);
        }
        JSONObject jsonObject = new JSONObject(String.valueOf(result));
        jsonObject.get("longitude");
        jsonObject.get("latitude");
        Map<String, String> recMap = new HashMap<>();

        recMap.put("longitude", jsonObject.getString("longitude"));
        recMap.put("latitude", jsonObject.getString("latitude"));

        return recMap;
    }


}
