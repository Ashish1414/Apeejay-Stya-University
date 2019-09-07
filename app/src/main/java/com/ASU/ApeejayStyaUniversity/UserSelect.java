package com.ASU.ApeejayStyaUniversity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.TextView;

public class UserSelect extends AppCompatActivity {
    TextView txtAsuUser,txtOtherUser;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_select);

        sharedPreferences = getSharedPreferences("LoginDetails",0);
        final int flag = sharedPreferences.getInt("flag",0);                    //sending this flag value to PinEntry Activity
        txtAsuUser=findViewById(R.id.txtAsuUsers);
        txtOtherUser=findViewById(R.id.txtOtherUsers);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        //For session we have set flag = 1
        txtAsuUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (flag == 1)                                  // means user already logged in, take him to PinEntry class
                {
                    Intent intent = new Intent(UserSelect.this , PinEntry.class);
                    startActivityForResult(intent, 0);
                    overridePendingTransition(0, 0);
                    finish();
                }
                else {                                                                      // else take the user to Login activity
                    Intent intent = new Intent(UserSelect.this, Login.class);
                    startActivityForResult(intent, 0);
                    overridePendingTransition(0, 0);
                    finish();
                }
            }
        });
    }

}
