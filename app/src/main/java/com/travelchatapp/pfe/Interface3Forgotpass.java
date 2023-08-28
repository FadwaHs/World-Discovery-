package com.travelchatapp.pfe;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;

import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class Interface3Forgotpass extends AppCompatActivity {

    Button buttonreset;
    TextView txtnewcode;
    TextView txtcodeconfirm;
    String Email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_interface3_forgotpass);

        buttonreset=findViewById(R.id.btnReset);
        txtnewcode=findViewById(R.id.Edpass);
        txtcodeconfirm=findViewById(R.id.EdConfirmpass);


        buttonreset.setOnClickListener(v -> {

            String newcode= txtnewcode.getText().toString();
            String newcodeconf= txtcodeconfirm.getText().toString();
            Email= getIntent().getStringExtra("Email");



            String type ="reset";
            ForgetPass3 forgetpass3 = new ForgetPass3(getApplicationContext());
            forgetpass3.execute(type, newcode, newcodeconf, Email);




            //OpenLoginPage();
        });
    }

    /*public void OpenLoginPage()
    {
        startActivity(new Intent(Interface3Forgotpass.this, Interface3Login.class));
        finish();
    }*/
}

