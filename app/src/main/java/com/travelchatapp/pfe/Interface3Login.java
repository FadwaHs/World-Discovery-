package com.travelchatapp.pfe;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;

import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Interface3Login extends AppCompatActivity {


    private Button btnlogin;
    EditText txtEmail;
    EditText txtpass;
    private TextView forgotPassword;
    private TextView signUp;


    private TextView welcomeText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_interace3_login);

        initialiseVar();
        onClickMethod();

    }

    private void initialiseVar() {
        btnlogin = findViewById(R.id.buttonlogin);
        forgotPassword = findViewById(R.id.interface3LogInFPsswrd);
        signUp = findViewById(R.id.interface3LogInSignUp);
        txtEmail = findViewById(R.id.LoginEmail);
        txtpass = findViewById(R.id.LoginPass);


        welcomeText = findViewById(R.id.welcomeText);

        welcomeText.setWidth((Constants.SCREEN_WIDTH / 4) * 2);
    }

    private void onClickMethod() {
        btnlogin.setOnClickListener(v -> {

            //String Email_User   = txtEmail.getText().toString();
            //String Password_User  = txtpass.getText().toString();
            //String type = "login";

            //PreferencesUtils.saveEmail(Email_User,this);
            //PreferencesUtils.savePassword(Email_User,this);
            Intent intent = new Intent(Interface3Login.this, Home.class);//Interface3Login//Home
            startActivity(intent);


            //BackgroundTaskLogin backgroundTasklogin = new BackgroundTaskLogin(getApplicationContext());
            //backgroundTasklogin.execute(type, Email_User, Password_User);

        });


        forgotPassword.setOnClickListener(view -> startActivity(new Intent(Interface3Login.this, Interface1Forgotpass.class)));
        signUp.setOnClickListener(view -> startActivity(new Intent(Interface3Login.this, Interface4Signup.class)));


    }





}
