package com.travelchatapp.pfe;

import android.content.Context;

import android.os.AsyncTask;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class ForgetPass1 extends AsyncTask <String, Void, String>   {

    Context context;


    ForgetPass1(Context ctxt)
    {
        this.context = ctxt;
    }

    @Override
    protected String doInBackground(String... strings) {

        String type = strings[0];


        String forget1URL ="http://10.0.2.2/PhpForAndroid/forgetpass1.php";

        if (type.equals("email"))
        {
            String email2 = strings[1];


            try {
                URL url = new URL(forget1URL);

                try {

                    HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);

                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8);
                    BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);

                    String send_data = URLEncoder.encode("email2", "UTF-8")+"="+URLEncoder.encode(email2, "UTF-8");

                    bufferedWriter.write(send_data);
                    bufferedWriter.flush();
                    bufferedWriter.close();

                    InputStream inputStream = httpURLConnection.getInputStream();
                    InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.ISO_8859_1);
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                    String result="";
                    String Line="";


                    //StringBuilder stringBuilder= new StringBuilder();


                    while ((Line=bufferedReader.readLine())!=null){
                        // stringBuilder.append(Line).append("\n");
                        result+=Line;


                    }

                    //result=stringBuilder.toString();
                    bufferedReader.close();

                    inputStream.close();

                    httpURLConnection.disconnect();

                    return result;


                } catch (IOException e) {
                    e.printStackTrace();
                }


            } catch (MalformedURLException e) {
                e.printStackTrace();
            }


        }
        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String result) {

        Toast.makeText(context, result, Toast.LENGTH_LONG).show();


        if(result.equals("account exist"))
        {

            Interface1Forgotpass.getInstance().OpenConfirmPage();

        }
        else
        {

        }


        //super.onPostExecute(s);



    }
}
