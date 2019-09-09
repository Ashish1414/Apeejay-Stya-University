package com.ASU.ApeejayStyaUniversity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class AboutASU extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_asu);
    }
    public void onBackPressed() {
        Intent intent = new Intent(AboutASU.this,ActivityChoice.class);
        startActivityForResult(intent, 0);
        overridePendingTransition(0, 0);
        finish();
    }
}
