package com.travelchatapp.pfe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class Interface1Forgotpass extends AppCompatActivity {

    Button button;
    TextView  Txtemail2;

    public static Interface1Forgotpass instance;

    public static Interface1Forgotpass getInstance()
    {
        return instance;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_interface1_forgotpass);


        instance=this;
        button = findViewById(R.id.btnConfirm);
        Txtemail2 = findViewById(R.id.forgetpassemail);

        button.setOnClickListener(v -> {


                String email2 =Txtemail2.getText().toString();
                String type = "email";

            ForgetPass1 forgetpass1 = new ForgetPass1(getApplicationContext());
            forgetpass1.execute(type, email2);

            //OpenConfirmPage();


        });

    }
     public void OpenConfirmPage()
     {
       Intent intent = new Intent(Interface1Forgotpass.this, Interface2Forgotpass.class);
       intent.putExtra("Email2", Txtemail2.getText().toString().trim());
       startActivity(intent);

     }

}

