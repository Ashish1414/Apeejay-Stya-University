package com.ASU.ApeejayStyaUniversity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.se.omapi.Session;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import java.net.PasswordAuthentication;
import java.util.Properties;

import de.hdodenhof.circleimageview.CircleImageView;

public class Login extends AppCompatActivity {
    CircleImageView profile_image_faculty , profile_image_student;
    TextView txt_who;
    EditText edt_auth;
    Button btnValidAuth;
    int aNumber;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnValidAuth = findViewById(R.id.btnValidAuth);

        profile_image_faculty = findViewById(R.id.profile_image_teacher);
        profile_image_student = findViewById(R.id.profile_image_student);

        txt_who = findViewById(R.id.txt_who);

        edt_auth = findViewById(R.id.edt_auth);

        


        profile_image_faculty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                txt_who.setText("Faculty Login");
                edt_auth.setHint("   Enter Your official e-mail Id");
            }
        });

        profile_image_student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                txt_who.setText("Student Login");
                edt_auth.setHint("   Enter Your Enrollment Number");
            }
        });

        btnValidAuth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendEmail();

            }
        });


    }

    public void sendEmail() {
        //Getting content for email
        String email = edt_auth.getText().toString().trim();
        String subject = "ASU OTP";

        aNumber = (int) ((Math.random() * 90000) + 10000);
        String message = "Dear Student\n\n "
                + aNumber +
                " is your one time password to authenticate yourself as an ASU user." +
                "\n In case this OTP request is not initiated by you," +
                " than you can simply ignore this or report to the undersigned.\n\n"
                + "Best Wishes"
                + "\n\nAshish Bansal and Team"
                + "\nashishbansal869@gmail.com\n"
                + "ASU Android App\n"
                +"Department of Computer Science and Engineering, "
                +"School Of Engineering and Teechnology"
                +"\nApeejay Stya University";

        //Creating SendMail object
        SendMail sm = new SendMail(this, email, subject, message, aNumber,this);

        //Executing sendmail to send email
        sm.execute();
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Login.this,UserSelect.class);
        startActivityForResult(intent, 0);
        overridePendingTransition(0, 0);
        finish();
    }
}
