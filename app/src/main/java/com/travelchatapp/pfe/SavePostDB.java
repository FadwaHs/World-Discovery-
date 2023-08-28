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

public class SavePostDB extends AsyncTask<Object, Object, String> {
    @Override
    protected String doInBackground(Object... objects) {

        String result1 = "";

        String imageId = (String) objects[0];
        String user = (String) objects[1];

        try {
            URL url = new URL(Constants.URL_7);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.setDoOutput(true);

            OutputStream outputStream = connection.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
            String data = URLEncoder.encode("image_id", "UTF-8") + "=" + URLEncoder.encode(imageId, "UTF-8") + "&&" +
                    URLEncoder.encode("users", "UTF-8") + "=" + URLEncoder.encode(user, "UTF-8");

            writer.write(data);
            writer.flush();
            writer.close();
            outputStream.flush();
            outputStream.close();

            InputStream inputStream = connection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            String line = "";
            String result = "";
            while ((line = reader.readLine()) != null) {
                result += line;
            }

            JSONArray jsonArray = new JSONArray(result);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                result1 = jsonObject.getString("results");
            }


        } catch (Exception ex) {
            Log.i("Error", ex.getMessage().toString());
        }

        return result1;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if (Constants.Flag == 1) {

        } else {
            HomePage.getInstance().savePost(s);
        }
    }
}
