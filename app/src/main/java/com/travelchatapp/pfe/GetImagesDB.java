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

public class GetImagesDB extends AsyncTask<Object, Object, Object> {

    String flag;

    @Override
    protected Object doInBackground(Object... objects) {

        String user = (String) objects[0];

        String line;
        String result = "";

        try {
            URL url = new URL(Constants.URL_2);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setDoInput(true);

            OutputStream outputStream = connection.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
            String data = URLEncoder.encode("users", "UTF-8") + "=" + URLEncoder.encode(user, "UTF-8");
            writer.write(data);
            writer.flush();
            writer.close();


            InputStream inputStream = connection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            if ((line = reader.readLine()) != null) {
                result += line;
            }

            inputStream.close();
            connection.disconnect();
            Constants.imageUrls.clear();
            JSONArray jsonArray = new JSONArray(result);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                Constants.imageUrls.add(Constants.FOLDER_IMAGE + jsonObject.get("Photo_Post"));
            }


        } catch (Exception ex) {
            Log.i("ERROR", ex.getMessage() + "");
        }

        return null;
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        //Profile.getInstance().setImage();
    }
}














