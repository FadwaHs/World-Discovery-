package com.travelchatapp.pfe;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    static MainActivity instance;

    static MainActivity getInstance() {
        return instance;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.splash_main);

        instance = this;

        Runnable runnable = new Runnable() {
            @Override
            public void run() {

                if (PreferencesUtils.getEmail(getApplicationContext())!=null) {
                    Intent intent = new Intent(MainActivity.this, Home.class);
                    startActivity(intent);
                }else
                {
                    Intent intent = new Intent(MainActivity.this, Interface3Login.class);//Interface3Login//Home
                    startActivity(intent);
                }

                overridePendingTransition(R.anim.anim2, R.anim.anim1);
                finish();

            }
        };

        /*1er arg c'est un runnable qui va comporter la liste des instructions creer dans le run()
        2nd arg c'est le temps aprés lequel on souhaite effectuer cette listes des instructions*/
        new Handler().postDelayed(runnable, 3000);


    }


}
