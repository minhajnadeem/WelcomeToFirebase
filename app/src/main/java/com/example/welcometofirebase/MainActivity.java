package com.example.welcometofirebase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.analytics.FirebaseAnalytics;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickAnalytics(View view) {
        Intent intent = new Intent(this,FirebaseAnalyticsActivity.class);
        startActivity(intent);
    }

    public void onClickAuthentication(View view) {
        Intent intent = new Intent(this,FirebaseAuthenticationActivity.class);
        startActivity(intent);
    }
}
