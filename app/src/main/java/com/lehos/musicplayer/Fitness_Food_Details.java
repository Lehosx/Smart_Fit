package com.lehos.musicplayer;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class Fitness_Food_Details extends AppCompatActivity {

//    ListView listView;
    TextView details_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fitness_food_details);

        details_text= findViewById(R.id.details_text);
        String details_story = getIntent().getStringExtra("details_story");
        details_text.setText(details_story);


    }

    public void food_details_back_button(View view) {
        Intent intent = new Intent(Fitness_Food_Details.this,Fitness_Food.class);
        startActivity(intent);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.fitness_menu,menu);
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
        Intent intent = new Intent(Fitness_Food_Details.this,Fitness_Food.class);
        startActivity(intent);
        finish();
    }
}