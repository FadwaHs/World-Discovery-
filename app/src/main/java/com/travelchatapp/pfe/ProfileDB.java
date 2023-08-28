package com.travelchatapp.pfe;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class ProfileDB extends AsyncTask<String, String, String> {


    @Override
    protected String doInBackground(String... strings) {

        String resulte = "";
        String data = "";

        try {
            URL url = new URL(Constants.URL_5);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setDoInput(true);

            OutputStream outputStream = connection.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
            if (Constants.Flag2) {
                data = URLEncoder.encode("users", "UTF-8") + "=" + URLEncoder.encode(Constants.EMAIL, "UTF-8");
            } else {
                data = URLEncoder.encode("users", "UTF-8") + "=" + URLEncoder.encode(Constants.USER_EMAIL, "UTF-8");
            }
            writer.write(data);
            writer.flush();
            writer.close();
            outputStream.flush();
            outputStream.close();

            InputStream inputStream = connection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            String line = "";
            while ((line = reader.readLine()) != null) {
                resulte += line;
            }

            JSONArray jsonArray = new JSONArray(resulte);
            JSONObject jsonObject = (JSONObject) jsonArray.get(0);
            resulte = jsonObject.getString("name_user") + ";" + jsonObject.getString("photo_profiles");


        } catch (Exception ex) {
            Log.i("ERROR", ex.getMessage() + "");
        }

        return resulte;
    }


    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if (!s.isEmpty() && !Constants.Flag2) {
            Profile.getInstance().setData(s);
        } else if (Constants.Flag2) {
            Constants.Flag2 = false;
            Profile2.getInstance().setData(s);
        }
    }
}

