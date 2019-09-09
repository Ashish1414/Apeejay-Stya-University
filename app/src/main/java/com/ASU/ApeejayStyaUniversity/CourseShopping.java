package com.ASU.ApeejayStyaUniversity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class CourseShopping extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener  {
    private DrawerLayout mDrawerlayout;
    private ActionBarDrawerToggle mToggle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_shopping);

        mDrawerlayout=findViewById(R.id.drawer);
        mToggle=new ActionBarDrawerToggle(this,mDrawerlayout,R.string.open,R.string.close);
        mDrawerlayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        NavigationView navigationView=findViewById(R.id.navigationId);
        navigationView.setNavigationItemSelectedListener(this);       // for making the items of navigation bar clickable
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {   //Without this icon i.e 3 lines will not work
        if(mToggle.onOptionsItemSelected(item))
            return true;
        return super.onOptionsItemSelected(item);
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        Intent intent;

        if(item.getItemId()==R.id.end_term)
        {
            intent = new Intent(this,CourseShopping.class);
            startActivity(intent);
            Toast.makeText(CourseShopping.this,"CourseShopping Activity",Toast.LENGTH_LONG).show();
        }
        else if(item.getItemId()==R.id.reg)
        {
            intent = new Intent(this,CourseShopping.class);
            startActivity(intent);
            Toast.makeText(CourseShopping.this,"CourseShopping Activity",Toast.LENGTH_LONG).show();
        }
        else if(item.getItemId()==R.id.verify)
        {
            intent = new Intent(this,ActivityChoice.class);
            startActivity(intent);
        }
        else if(item.getItemId()==R.id.drop)
        {
            intent = new Intent(this,ActivityChoice.class);
            startActivity(intent);
        }
        else if(item.getItemId()==R.id.result)
        {
            intent = new Intent(this,ActivityChoice.class);
            startActivity(intent);
        }
        else if(item.getItemId()==R.id.repeat)
        {
            intent = new Intent(this,CourseShopping.class);
            startActivity(intent);
            Toast.makeText(CourseShopping.this,"CourseShopping Activity",Toast.LENGTH_LONG).show();
        }
        return false;
    }

}
