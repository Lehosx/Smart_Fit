package com.lehos.musicplayer;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;



public class Fitness_Food extends AppCompatActivity {

    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fitness_food);

        String[] title_story = getResources().getStringArray(R.array.title_story);
        final String[] detail_story = getResources().getStringArray(R.array.details_story);

        listView = findViewById(R.id.list);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.row_nutritions,R.id.row_text,title_story);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String t = detail_story[position];
                Intent intent = new Intent(Fitness_Food.this,Fitness_Food_Details.class);
                intent.putExtra("story",t);
                startActivity(intent);
            }
        });

    }

    public void food_go_back(View view) {
        Intent intent = new Intent(Fitness_Food.this,Fitness_home.class);
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
        Intent intent = new Intent(Fitness_Food.this,Fitness_home.class);
        startActivity(intent);
        finish();
    }
}