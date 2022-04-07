package com.example.covid19tracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

public class splashScreen extends AppCompatActivity {
    int SPLASH_TIME = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        String flag = sharedPreferences.getString("flag", "");
        String tvverify = "verify";
        Log.e("text",flag.toString());
        new Handler().postDelayed(() -> {
            if (flag.equals("verify")) {
                Intent intent = new Intent(this, Verify.class);
                startActivity(intent);
                finish();
            } else {
                Intent mySuperIntent = new Intent(this, MainActivity.class);
                startActivity(mySuperIntent);
                finish();
            }
        }, SPLASH_TIME);
    }
}
