package com.lehos.musicplayer;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class AfterAge18 extends AppCompatActivity {


    int[] newArray;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_age18);


        Toolbar toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);

        newArray = new int[]{
                R.id.bow_pose,R.id.bridge_pose,R.id.chair_pose,
                R.id.child_pose,R.id.cobbler_pose,R.id.cow_pose,
                R.id.playji_pose,R.id.pauseji_pose,R.id.plank_pose,
                R.id.crunches_pose,R.id.situp_pose,R.id.rotation_pose,
                R.id.twist_pose,R.id.windmill_pose,R.id.legup_pose,
        };
    }

    public void Imagebuttonclicked(View view){
        for (int i=0; i<newArray.length;i++){

            if (view.getId() == newArray[i]){
                int value  = i+1;
                Log.i("FIRST",String.valueOf(value));
                Intent intent = new Intent(AfterAge18.this,AfterAge18_exercises.class);
                intent.putExtra("value", String.valueOf(value));
                startActivity(intent);
            }

        }
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


    public void back_img_btn(View view) {
        Intent intent = new Intent(AfterAge18.this,Fitness_home.class);
        startActivity(intent);
        finish();
    }
}