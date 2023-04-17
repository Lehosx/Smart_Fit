package com.lehos.musicplayer;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class Fitness_home extends AppCompatActivity {
    Button button1, button2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fitness_home);

        Toolbar toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        button1 = findViewById(R.id.startyoga1);
        button2 = findViewById(R.id.startyoga2);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Fitness_home.this,BeforeAge18.class);
                startActivity(intent);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Fitness_home.this,AfterAge18.class);
                startActivity(intent);
            }
        });
    }

    public void beforeage18(View view) {
        Intent intent = new Intent(this,BeforeAge18.class);
        startActivity(intent);

    }

    public void afterage18(View view) {
        Intent intent = new Intent(this,AfterAge18.class);
        startActivity(intent);
    }


    public void food(View view) {
        Intent intent = new Intent(this,Fitness_Food.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.fitness_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id= item.getItemId();

        if(id == R.id.id_privacy){
            Toast.makeText(this, "Privacy", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://iotexpert1.blogspot.com/2020/10/weight-loss-privacy-ploicy-page.html"));
            startActivity(intent);
            return true;
        }

        if(id == R.id.id_term){
            Toast.makeText(this, "Terms and Condition", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://iotexpert1.blogspot.com/2020/10/weight-loss-terms-and-conditions-page.html"));
            startActivity(intent);
            return true;
        }

        if(id == R.id.id_rate){
            Toast.makeText(this, "rate us", Toast.LENGTH_SHORT).show();
            try {
                startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("market://details?id=" + getPackageName())));
            }
            catch (Exception ex){
                startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("https://play.google.com/store/apps/details?id=" + getPackageName())));
            }
            return true;
        }

        if(id == R.id.id_more){
            Toast.makeText(this, "more apps", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/developer?id=Leap+Fitness+Group"));
            startActivity(intent);
            return true;
        }

        if(id == R.id.id_share){
            Toast.makeText(this, "share", Toast.LENGTH_SHORT).show();
            Intent myIntent = new Intent(Intent.ACTION_SEND);
            myIntent.setType("text/plain");
            String share_body = "This is the best app for fitness \n" +
                    "https://play.google.com/store/apps/details?id=com.lehos.musicplayer&h1=en";
            String share_hub = "Smart Fit";
            myIntent.putExtra(Intent.EXTRA_SUBJECT,share_hub);
            myIntent.putExtra(Intent.EXTRA_TEXT,share_body);
            startActivity(Intent.createChooser(myIntent, "Share using"));
            return true;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Fitness_home.this,HomeActivity.class);
        startActivity(intent);
        finish();
    }

}