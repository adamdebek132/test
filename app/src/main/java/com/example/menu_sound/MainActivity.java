package com.example.menu_sound;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    ImageView menu_img;
    MediaPlayer menu_song;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        menu_img = findViewById(R.id.imageView);
        menu_song = MediaPlayer.create(MainActivity.this, R.raw.menumusic);
        menu_song.start();
        //menu_song.setLooping(true);
    }
    public void openNewGameActivity (View view){
        Intent intent = new Intent(this, Quiz.class);
        startActivity(intent);
    }
    public void openOptionsActivity (View view){
        Intent intent = new Intent(this, Options.class);
        startActivity(intent);
        //menu_song.stop();
    }

    @Override
    protected void onPause() {
        super.onPause();
        menu_song.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(menu_song!=null){
            menu_song.start();
        }
    }
}