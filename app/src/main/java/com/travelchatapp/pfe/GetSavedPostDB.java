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
import java.util.ArrayList;

public class GetSavedPostDB extends AsyncTask<String, ArrayList<PlaceItem>, ArrayList<PlaceItem>> {


    @Override
    protected ArrayList<PlaceItem> doInBackground(String... strings) {

        String result = "";
        String line = "";
        ArrayList<PlaceItem> placeItems = new ArrayList<>();


        try {
            URL url = new URL(Constants.URL_11);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setDoInput(true);

            OutputStream outputStream = connection.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
            String data = URLEncoder.encode("user", "UTF-8") + "=" + URLEncoder.encode(Constants.USER_EMAIL, "UTF-8");
            writer.write(data);
            writer.flush();
            writer.close();
            outputStream.flush();
            outputStream.close();


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
                placeItems.add(new PlaceItem(jsonObject.getString("id_Post"), Constants.FOLDER_IMAGE + jsonObject.getString("photo_post"), jsonObject.getString("Nom_Categorie")
                        , jsonObject.getString("description_post"), Constants.FOLDER_IMAGE + jsonObject.getString("photo_profiles"), jsonObject.getString("Name_User"),
                        jsonObject.getString("date_")));
            }

        } catch (Exception ex) {
            Log.i("ERROR", ex.getMessage() + "");
        }

        return placeItems;
    }


    @Override
    protected void onPostExecute(ArrayList<PlaceItem> placeItems) {
        super.onPostExecute(placeItems);
        if (!placeItems.isEmpty()) {
            SavedPost.getInstance().setData(placeItems);
        }
    }
}

