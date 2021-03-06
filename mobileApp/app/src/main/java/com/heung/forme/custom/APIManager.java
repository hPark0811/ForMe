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

    static final String URL = "http://54.165.197.19:8080/customers/";

    public static Map<String, String> getCustomerRecommendation(String customerID) throws Exception {
        StringBuilder result = new StringBuilder();
        URL url = new URL(URL + customerID + "/recommendations");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setConnectTimeout(1000);
        InputStream in = new BufferedInputStream(con.getInputStream());

        BufferedReader reader = new BufferedReader(new InputStreamReader(in));

        String line;
        while ((line = reader.readLine()) != null) {
            result.append(line);
        }
        JSONObject jsonObject = new JSONObject(String.valueOf(result));
        Map<String, String> recMap = new HashMap<>();

        recMap.put("creditCard", jsonObject.getString("creditCard"));
        recMap.put("bankAccount", jsonObject.getString("bankAccount"));

        return recMap;
    }

    public static Map<String, Double> getClosestBranch(String customerID) throws Exception {
        StringBuilder result = new StringBuilder();
        URL url = new URL(URL + customerID + "/location");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setConnectTimeout(1000);
        InputStream in = new BufferedInputStream(con.getInputStream());

        BufferedReader reader = new BufferedReader(new InputStreamReader(in));

        String line;
        while ((line = reader.readLine()) != null) {
            result.append(line);
        }
        JSONObject jsonObject = new JSONObject(String.valueOf(result));
        Map<String, Double> recMap = new HashMap<>();

        recMap.put("customerLat", jsonObject.getDouble("customerLat"));
        recMap.put("customerLong", jsonObject.getDouble("customerLong"));
        recMap.put("branchLat", jsonObject.getDouble("branchLat"));
        recMap.put("branchLong", jsonObject.getDouble("branchLong"));
        return recMap;
    }


}
