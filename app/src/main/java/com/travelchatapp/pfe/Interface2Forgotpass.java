package com.travelchatapp.pfe;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;

import android.widget.Button;
import android.widget.TextView;

public class Interface2Forgotpass extends AppCompatActivity {



    Button btnext;
    TextView txtcode2;
    String  Email2;

    public static Interface2Forgotpass instance ;



    public static Interface2Forgotpass getInstance()
    {
        return instance;
    }


    public Interface2Forgotpass() {

    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interface2_forgotpass);

          instance=this;
          btnext= findViewById(R.id.btnNext);
          txtcode2= findViewById(R.id.forgetpasswcode);




        btnext.setOnClickListener(v -> {

                 String code2= txtcode2.getText().toString();
                  Email2 = getIntent().getStringExtra("Email2");

                 String type ="code";
                 ForgetPass2 forgetpass2 = new ForgetPass2(getApplicationContext());
                 forgetpass2.execute(type, code2, Email2);


             //OpenResetPage();



          });


    }

    public void OpenResetPage()
    {
        Intent intent = new Intent(Interface2Forgotpass.this, Interface3Forgotpass.class);
        intent.putExtra("Email",Email2);
        startActivity(intent);

    }
}
