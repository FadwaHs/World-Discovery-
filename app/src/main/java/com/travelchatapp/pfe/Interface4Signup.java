package com.travelchatapp.pfe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;

import android.os.Bundle;
import android.util.Patterns;

import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;


import java.util.regex.Pattern;

public class Interface4Signup extends AppCompatActivity {

    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    //"(?=.*[0-9])" +         //at least 1 digit
                    //"(?=.*[a-z])" +         //at least 1 lower case letter
                    //"(?=.*[A-Z])" +         //at least 1 upper case letter
                    "(?=.*[a-zA-Z])" +      //any letter
                    "(?=\\S+$)" +           //no white spaces
                    ".{4,}" +               //at least 4 characters
                    "$");



    EditText txtName ;
    EditText txtLocation;
    EditText txtEmail;
    EditText txtPass;
    Button btnSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_interface4_signup);

        initialiseVar();

        btnSignup =findViewById(R.id.btnSignup);
        txtName = findViewById(R.id.SignupName);
        txtLocation =findViewById(R.id.SignupLocation);
        txtEmail =findViewById(R.id.SignupEmail);
        txtPass = findViewById(R.id.SignupPass);



        btnSignup.setOnClickListener(v -> {

            if(!validateUsername() | !validateLocation() | !validateEmail() | !validatePassword())
            {
                return;
            }



            String name = txtName.getText().toString();
            String location = txtLocation.getText().toString();
            String email = txtEmail.getText().toString();
            String password = txtPass.getText().toString();
            String type = "reg";

            Constants.USER_EMAIL=txtEmail.getText().toString();

            BackgroundTask backgroundTask = new BackgroundTask(getApplicationContext());
            backgroundTask.execute(type, name, location, email, password);

        });


    }


    private void initialiseVar() {

        /*createAccountText = findViewById(R.id.createAccountText);

        createAccountText.setWidth((Constants.SCREEN_WIDTH / 4) * 2);*/
    }



    public void OpenLoginPage(View view) {
        startActivity(new Intent(Interface4Signup.this, Interface3Login.class));
        finish();
    }


    /* Validate inputs */

    private boolean validateEmail()
    {
        String emailinput = txtEmail.getText().toString().trim();
        if(emailinput.isEmpty())
        {
            txtEmail.setError("Field can't be empty");
            return false;
        }else if(!Patterns.EMAIL_ADDRESS.matcher(emailinput).matches()){
            txtEmail.setError("Please enter a valid email address");
            return false;
        }
        else{

            txtEmail.setError(null);
            return true;
        }


    }

    private boolean validateUsername()
    {
        String usernameinput = txtName.getText().toString().trim();

        if(usernameinput.isEmpty())
        {
            txtName.setError("Field can't be empty");
            return false;
        }else if(usernameinput.length() > 25)
        {
            txtName.setError("Username too long");
            return false;
        }else{
            txtName.setError(null);
            return true;
        }
    }

    private boolean validatePassword()
    {
        String passwordinput =txtPass.getText().toString().trim();
        if(passwordinput.isEmpty())
        {
            txtPass.setError("Field can't be empty");
            return false;
        }else if(!PASSWORD_PATTERN.matcher(passwordinput).matches())
        {
            txtPass.setError("Password too weak");
            return false;
        }
        else{
            txtPass.setError(null);
            return true;
        }
    }

    private boolean validateLocation()
    {
        String locationinput =txtLocation.getText().toString().trim();
        if(locationinput.isEmpty())
        {
            txtLocation.setError("Field can't be empty");
            return false;
        }else{
            txtLocation.setError(null);
            return true;
        }
    }


}


