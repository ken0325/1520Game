package com.example.a1520game;

import android.util.*;

import org.json.*;

import java.io.*;
import java.net.*;
import java.util.List;
import java.util.Locale;

public class ApiController extends Thread {
    private static final String TAG = "ApiController";
    public static final DataModel dataModel = new DataModel();

    public static String makeRequest() {
        String response = null;
        String jsonUrl = "https://assign-mobileasignment-ihudikcgpf.cn-hongkong.fcapp.run";

        try {
            URL url = new URL(jsonUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            // Read the response
            InputStream in = new BufferedInputStream(conn.getInputStream());
            response = publicInputStreamToString(in);
        } catch (MalformedURLException e) {
            Log.e(TAG, "MalformedURLException: " + e.getMessage());
        } catch (ProtocolException e) {
            Log.e(TAG, "ProtocolException: " + e.getMessage());
        } catch (IOException e) {
            Log.e(TAG, "IOException: " + e.getMessage());
        } catch (Exception e) {
            Log.e(TAG, "Exception: " + e.getMessage());
        }
        return response;
    }

    public static String publicInputStreamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line = "";
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line).append('\n');
            }
        } catch (IOException e) {
            Log.e(TAG, "IOException: " + e.getMessage());
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                Log.e(TAG, "IOException: " + e.getMessage());
            }
        }
        return sb.toString();
    }

    public void run() {
        String data = makeRequest();
        Log.e(TAG, "Response from url: " + data);
        if (data != null) {
            try {
                JSONObject jsonObj = new JSONObject(data);
                dataModel.setOpponentLeft(jsonObj.getInt("left"));
                dataModel.setOpponentRight(jsonObj.getInt("right"));
                dataModel.setOpponentGuess(jsonObj.getInt("guess"));
            } catch (final JSONException e) {
                Log.e(TAG, "Json parsing error: " + e.getMessage());
            }
        } else {
            Log.e(TAG, "Couldn't get json data from url.");
        }
    }
}