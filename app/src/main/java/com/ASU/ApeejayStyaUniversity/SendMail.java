package com.ASU.ApeejayStyaUniversity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

//We have made SendMail as class and not activity becoz we don't want to show what is happening in the background and classes dosent add to Mainifest file

//Class is extending AsyncTask because this class is going to perform a networking operation
public class SendMail extends AsyncTask<Void, Void, Void> {

    //Declaring Variables
    private Context context;
    private Session session;             //Session help you when want to store user data outside your application, so that when the next time user use your application, you can easily get back his details and perform accordingly.

    //Information to send email
    private String email;
    private String subject;
    private String message;
    private int aNumber;   //otp
    Activity activity;

    //Progressdialog to show while sending email
    private ProgressDialog progressDialog;

    //Class Constructor
    public SendMail(Context context, String email, String subject, String message, int aNumber, Activity activity){    // using constructor we are getting values from LoginActivity
        //Initializing variables
        this.context = context;
        this.email = email;
        this.subject = subject;
        this.message = message;
        this.aNumber = aNumber;
        this.activity = activity;
    }

    @Override
    protected void onPreExecute() {  //This method contains the code which is executed before the background processing starts   [Before doing background operation we should show something on screen like progressbar or any animation to user]
        super.onPreExecute();
        //Showing progress dialog while sending email
        progressDialog = ProgressDialog.show(context,"Sending message","Please wait...",false,false);
    }

    @Override
    protected void onPostExecute(Void aVoid) {                  //In this method we can update ui of background operation result.
        super.onPostExecute(aVoid);
        //Dismissing the progress dialog
        progressDialog.dismiss();
        //Showing a success message
        Toast.makeText(context,"Message Sent", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(context , OtpVerification.class);
        intent.putExtra("OTP",aNumber);    //intent.putExtra(String key, Object data) method to save data in it.Passing the Otp to OtpVerification.class
        context.startActivity(intent);
        activity.finish();
    }

    @Override
    protected Void doInBackground(Void... params) {         //In this method we have to do background operation on background thread. Mostly we do some long running tasks like authenticating
        //Creating properties
        Properties props = new Properties();

        //Configuring properties for gmail
        //If you are not using gmail you may need to change the values
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "458");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.port", "587");
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.starttls.required", "true");
        props.put("mail.debug", "true");
        props.put("mail.smtp.ssl.enable", "true");
        props.put("X-Priority","1");   //1- very high 2-high , 3 -medium ,4-low , 5 - verylow

        //Creating a new session
        session = Session.getDefaultInstance(props,                                  //public static Session getDefaultInstance(Properties props,Authenticator auth)-returns the default session.
                new javax.mail.Authenticator() {
                    //Authenticating the password and id and logging in to email id ,email id and password are in config java class
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(Config.EMAIL, Config.PASSWORD);
                    }
                });

        try {
            //Creating MimeMessage object                 //The javax.mail.Message class provides methods to compose the message. But it is an abstract class so its subclass javax.mail.internet.MimeMessage class is mostly used.
            MimeMessage mm = new MimeMessage(session);

            //Setting sender address
            mm.setFrom(new InternetAddress(Config.EMAIL));
            //Adding receiver
            mm.addRecipient(Message.RecipientType.TO, new InternetAddress(email));   // we had get this email in  LoginActivity by edt_auth.getText().toString().trim();
            //Adding subject
            mm.setSubject(subject);
            //Adding message
            mm.setText(message);

            //Sending email
            Transport.send(mm);

        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
