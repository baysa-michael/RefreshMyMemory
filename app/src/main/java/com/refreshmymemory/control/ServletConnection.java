package com.refreshmymemory.control;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

public class ServletConnection extends AsyncTask<String, Integer, Boolean> {
    private static final String TAG = "ServletConnection";
    private String urlString;
    private String requestParameters;
    private ServletConnectionListener listener;

    public ServletConnection (Map<String, String> requestData,
                              ServletConnectionListener newListener) throws Exception {
        StringBuilder parameterBuilder = new StringBuilder();
        boolean notFirstItem = false;

        // Loop through and build a parameter string for a URL connection
        for (String key : requestData.keySet()) {
            try {
                if (notFirstItem) {
                    // Add an '&' between each additional new element
                    parameterBuilder.append("&");
                } else {
                    notFirstItem = true;
                }

                // Add the new parameter
                parameterBuilder.append(key).append("=")
                        .append(URLEncoder.encode(requestData.get(key), "UTF-8"));
            } catch (Exception e) {
                throw new Exception(e.getMessage());
            }
        }

        // Attach the requestParameters to the object
        requestParameters = parameterBuilder.toString();

        // Attach the listener
        this.listener = newListener;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Boolean doInBackground(String... parameters) {
        // Set URL from Passed Parameter
        urlString = parameters[0];
        HttpURLConnection urlConnection = null;
        boolean isSuccessful = false;

        try {
            // Create a new URL Object
            URL url = new URL(urlString);

            // Create a new secure connection
            urlConnection = (HttpURLConnection) url.openConnection();

            // Prepare URL Connection Parameters
            urlConnection.setDoOutput(true);
            urlConnection.setRequestMethod("GET");
            urlConnection.setRequestProperty("Accept-Charset", "UTF-8");

            // Set Read and Connection Timeout (in milliseconds)
            urlConnection.setReadTimeout(10000);
            urlConnection.setConnectTimeout(15000);

            // Start Connection
            urlConnection.connect();

            // Create a DataOutputStream to send data
            DataOutputStream out = new DataOutputStream(urlConnection.getOutputStream());

            // Write the requestParameters
            out.writeBytes(requestParameters);

            // Flush and Close DataOutputStream
            out.flush();
            out.close();


            // Retrieve Results from Server via InputStream
            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

            // Build Input
            String returnString = "";
            String returnJSON = "";
            while ((returnString = reader.readLine()) != null) {
                returnJSON += returnString;
            }

            // Retrieve Status from JSON Result
            JsonObject json = new Gson().fromJson(returnJSON, JsonObject.class);
            String status = json.get("status").toString();
            if (status.equalsIgnoreCase("SUCCESS")) {
                isSuccessful = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }

        return isSuccessful;
    }

    @Override
    protected void onPostExecute(Boolean isSuccessful) {
        super.onPostExecute(isSuccessful);

        if (isSuccessful) {
            listener.onServerResponse(true, "Successfully Added New User");
        } else {
            listener.onServerResponse(false, "ERROR:  Unable to Add New User");
        }

    }
}
