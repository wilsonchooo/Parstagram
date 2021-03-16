package com.example.parstagram;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class activity_login extends AppCompatActivity {

    public static final String TAG = "login_activity";
    private EditText etUsername;
    private EditText etPassword;
    private Button btnLogin;
    private ImageView instagramlogo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if(ParseUser.getCurrentUser()!=null){
            goMainActivity();
        }
        etUsername=findViewById(R.id.etUsername);
        etPassword=findViewById(R.id.etPassword);
        btnLogin=findViewById(R.id.btnLogin);
        instagramlogo=findViewById(R.id.InstagramLogo);
        Glide.with(this).load("https://logos-world.net/wp-content/uploads/2020/04/Instagram-Logo.png").into(instagramlogo);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG,"onClick Login button");
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();
                loginUser(username,password);
            }
        });
    }

    private void loginUser(String username, String password){
        Log.i(TAG,"attempting to log in" + username);
        ParseUser.logInInBackground(username, password, new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                if(e!=null){
                    Log.e(TAG,"issue with login",e);
                    Toast.makeText(activity_login.this,"Issue with log in!", Toast.LENGTH_LONG).show();
                    return;
                }
                goMainActivity();
                Toast.makeText(activity_login.this,"Success!", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void goMainActivity(){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();

    }
}