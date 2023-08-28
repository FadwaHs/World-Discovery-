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

public class GetImageDB extends AsyncTask<Integer, Object, ArrayList<PlaceItem>> {

    String line = "";
    String result = "";
    ArrayList<PlaceItem> placeItems = new ArrayList<>();
    int id;

    @Override
    protected ArrayList<PlaceItem> doInBackground(Integer... integers) {
        id = integers[0];

        try {

            URL url = new URL(Constants.URL_9);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoInput(true);
            connection.setDoInput(true);

            OutputStream outputStream = connection.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
            String data = URLEncoder.encode("user_email", "UTF-8") + "=" + URLEncoder.encode(Constants.USER_EMAIL, "UTF-8") + "&&"
                    + URLEncoder.encode("id", "UTF-8") + "=" + URLEncoder.encode(Constants.IMAGE_ID, "UTF-8");
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

                placeItems.add(new PlaceItem(jsonObject.getString("id_Post"), Constants.FOLDER_IMAGE + jsonObject.getString("Photo_Post"),
                        jsonObject.getString("nom_category") + ";" + jsonObject.getString("City_Post") + ";" + jsonObject.getString("Email_User")
                                + ";" + jsonObject.getString("date_") + ";" + jsonObject.getString("Titre_Post"),
                        jsonObject.getString("Description_Post"), Constants.FOLDER_IMAGE + jsonObject.getString("Photo_Profil"),
                        jsonObject.getString("Name_User"), jsonObject.getString("Location_User")));
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
        if (id == 1) {
            PostFragement.getInstance().data(placeItems);
        } else if (id == 2) {
            PostFragement2.getInstance().data(placeItems);
        }else {
            PostFragement3.getInstance().data(placeItems);
        }
    }
}



























