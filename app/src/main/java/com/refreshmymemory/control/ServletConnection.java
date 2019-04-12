package com.refreshmymemory.control;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

public class ServletConnection extends AsyncTask<String, Integer, Void> {
    private static final String TAG = "ServletConnection";
    private String urlString;
    private String requestParameters;
    // ******************************* NEED TO IMPLEMENT A LISTENER CONNECTED TO THE UI ***********

    public ServletConnection (Map<String, String> requestData) throws Exception {
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
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Void doInBackground(String... parameters) {
        // Set URL from Passed Parameter
        urlString = parameters[0];
        HttpsURLConnection urlConnection = null;

        try {
            // Create a new URL Object
            URL url = new URL(urlString);

            // Create a new secure connection
            urlConnection = (HttpsURLConnection) url.openConnection();

            // Prepare URL Connection Parameters
            urlConnection.setDoOutput(true);
            urlConnection.setRequestMethod("POST");
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
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));

            // Build Input
            StringBuilder result = new StringBuilder();
            String returnJSON;
            while ((returnJSON = reader.readLine()) != null) {
                result.append(returnJSON);
            }

            // **************** ADD ACTIONS FOR RETURN DOCUMENT ***********************
            Log.i(TAG, returnJSON);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }

        return null ;
    }
}
