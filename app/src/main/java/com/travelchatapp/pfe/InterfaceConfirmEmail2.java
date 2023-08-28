package com.travelchatapp.pfe;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

public class InterfaceConfirmEmail2 extends AppCompatActivity {

    Button btnSignin;
    EditText Txtcode;
    EditText Txtemail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_interface_confirm_email2);


        btnSignin=findViewById(R.id.btnSignin);
        Txtcode= findViewById(R.id.ConfirmEmailEditText);
        Txtemail = findViewById(R.id.EdEmailconfirm);
        Txtemail.setText(Constants.USER_EMAIL);

        btnSignin.setOnClickListener(v -> {

             String code= Txtcode.getText().toString();
             String Email = Txtemail.getText().toString();

             String type = "conf";

            BackgroundTaskEmail backgroundTaskEmail = new BackgroundTaskEmail(getApplicationContext());
            backgroundTaskEmail.execute(type, code, Email);



        });
    }
}
