package com.ASU.ApeejayStyaUniversity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ActivityChoice extends AppCompatActivity {
    Button btnLogOut;
    SharedPreferences sharedPreferences;
    CardView cardView_apricot;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice);

        cardView_apricot=findViewById(R.id.apricot);
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
        cardView_apricot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Apricot.class);
                startActivity(intent);
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
