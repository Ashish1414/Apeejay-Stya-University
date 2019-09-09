package com.ASU.ApeejayStyaUniversity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ActivityChoice extends AppCompatActivity {
    Button btnLogOut;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice);
        sharedPreferences = getSharedPreferences("LoginDetails", Context.MODE_PRIVATE);
        btnLogOut = findViewById(R.id.btnLogOut);
        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.commit();
                onBackPressed();

            }
        });
    }
    public void onBackPressed() {
        Intent intent = new Intent(ActivityChoice.this,UserSelect.class);
        startActivityForResult(intent, 0);
        overridePendingTransition(0, 0);
        finish();
    }
}
