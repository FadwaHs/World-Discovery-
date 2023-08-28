package com.travelchatapp.pfe;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class AddProfileImageDB extends AsyncTask<Object, String, String> {


    @Override
    protected String doInBackground(Object... objects) {

        String s = new String();
        String s1 = new String();
        String line = new String();
        String result = new String();
        Bitmap bitmap = (Bitmap) objects[0];

        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
            byte[] bytes = byteArrayOutputStream.toByteArray();
            s1 = Base64.encodeToString(bytes, Base64.DEFAULT);


            URL url = new URL(Constants.URL_12);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setDoInput(true);

            OutputStream stream = connection.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(stream, "UTF-8"));
            String data = URLEncoder.encode("users", "UTF-8") + "=" + URLEncoder.encode(Constants.USER_EMAIL, "UTF-8") + "&&" +
                    URLEncoder.encode("images", "UTF-8") + "=" + URLEncoder.encode(s1, "UTF-8");
            writer.write(data);
            writer.flush();
            writer.close();
            stream.close();

            InputStream stream1 = connection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream1, "ISO-8859-1"));
            while ((line = reader.readLine()) != null) {
                result += line;
            }

            JSONArray jsonArray = new JSONArray(result);
            JSONObject jsonObject = (JSONObject) jsonArray.get(0);
            s = jsonObject.getString("results");

            reader.close();
            stream1.close();
            connection.disconnect();

        } catch (Exception e) {
            Log.i("Error", e.getMessage().toString());
        }

        return s;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        Toast.makeText(Profile.getInstance().getContext(), "Updated", Toast.LENGTH_LONG).show();

    }
}
