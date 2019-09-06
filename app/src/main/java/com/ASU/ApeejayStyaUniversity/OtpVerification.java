package com.ASU.ApeejayStyaUniversity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class OtpVerification extends AppCompatActivity {
    Intent intent;
    Bundle bundle;
    int numOtp;
    Button btnVerify;
    EditText edtTextCode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_verification);
        intent=getIntent();
        bundle=intent.getExtras();
        numOtp=bundle.getInt("OTP");

        edtTextCode=findViewById(R.id.edtTextCode);

        btnVerify = findViewById(R.id.buttonSignIn);

        btnVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String code = edtTextCode.getText().toString().trim();

                if (code.isEmpty() || code.length() < 5) {

                    edtTextCode.setError("Enter code...");
                    edtTextCode.requestFocus();
                }

                else {
                    if(code.equals(""+numOtp))
                    {

                        Intent intent = new Intent(OtpVerification.this, PinEntry.class);
                        startActivity(intent);
                        finish();
                    }

                    else {
                        Toast.makeText(OtpVerification.this, "Invalid OTP", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(OtpVerification.this,UserSelect.class);
        startActivityForResult(intent, 0);
        overridePendingTransition(0, 0);
        finish();
    }
}
