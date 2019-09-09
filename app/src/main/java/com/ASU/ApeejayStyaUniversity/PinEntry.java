package com.ASU.ApeejayStyaUniversity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class PinEntry extends AppCompatActivity {
    int flag;
    TextView txtPin;
    EditText edtPin;
    Button btnPinSave;
    String Pin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pin_entry);
        txtPin = findViewById(R.id.txtPin);
        edtPin = findViewById(R.id.edtPin);
        btnPinSave = findViewById(R.id.btnSavePin);
        final SharedPreferences sharedPreferences = getSharedPreferences("LoginDetails", Context.MODE_PRIVATE);
        //For receving data
        flag = sharedPreferences.getInt("flag", 0);
        Pin = sharedPreferences.getString("PIN","");
        if (flag == 0) {
            txtPin.setText("Create Your New Pin");
            btnPinSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (edtPin.length()<4)
                    {
                        Toast.makeText(PinEntry.this, "Enter 4 digit pin only", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        //Sending data to same activity PinEntry
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putInt("flag", 1);    // used for saving value
                        editor.putString("PIN", edtPin.getText().toString());
                        editor.commit();

                        Intent intent = new Intent(PinEntry.this,ActivityChoice.class);
                        startActivityForResult(intent, 0);
                        overridePendingTransition(0, 0);
                        finish();
                    }
                }
            });

        }

        else {
            btnPinSave.setVisibility(View.INVISIBLE);
            edtPin.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    if (edtPin.length()==4)
                    {
                     if (edtPin.getText().toString().equals(Pin))
                     {
                         Intent intent = new Intent(PinEntry.this,ActivityChoice.class);
                         startActivityForResult(intent, 0);
                         overridePendingTransition(0, 0);
                         finish();
                     }
                    }
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(PinEntry.this,UserSelect.class);
        startActivityForResult(intent, 0);
        overridePendingTransition(0, 0);
        finish();
    }
}
