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

public class GetSearchPostDB extends AsyncTask<String , Object, ArrayList<PlaceItem>> {

    String line = "";
    String result = "";
    ArrayList<PlaceItem> placeItems = new ArrayList<>();
    String search;

    @Override
    protected ArrayList<PlaceItem> doInBackground(String... strings) {
        search = strings[0];

        try {

            URL url = new URL(Constants.URL_13);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoInput(true);
            connection.setDoInput(true);

            OutputStream outputStream = connection.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
            String data = URLEncoder.encode("search", "UTF-8") + "=" + URLEncoder.encode(search, "UTF-8");
            writer.write(data);
            writer.flush();
            writer.close();
            outputStream.flush();
            outputStream.close();

            InputStream inputStream = connection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            while ((line = reader.readLine()) != null) {
                result += line;
            }
            JSONArray jsonArray = new JSONArray(result);
            placeItems.clear();
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = (JSONObject) jsonArray.get(i);

                placeItems.add(new PlaceItem(jsonObject.getString("id_Post") + ";" + jsonObject.getString("Email_User") + ";" + jsonObject.getString("date_"),
                        Constants.FOLDER_IMAGE + jsonObject.getString("Photo_Post"), jsonObject.getString("Nom_Categorie"), jsonObject.getString("Description_Post"),
                        Constants.FOLDER_IMAGE + jsonObject.getString("Photo_Profil"), jsonObject.getString("Name_User"), jsonObject.getString("Location_User")));
            }

            reader.close();
            inputStream.close();
            connection.disconnect();

        } catch (Exception ex) {
            Log.i("Error", ex.getMessage().toString());
        }

        return placeItems;
    }

    @Override
    protected void onPostExecute(ArrayList<PlaceItem> placeItems) {
        super.onPostExecute(placeItems);
        if (!placeItems.isEmpty()) {
            SearchFragement.getInstance().data(placeItems);
        }
    }
}