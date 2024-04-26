package com.example.a51cp2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Homepage extends AppCompatActivity {

    List<String> playlists = new ArrayList<>();
    EditText link;
    Button playButton, addButton, playlistButton;
    String username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        link = findViewById(R.id.YTlink);
        username = getIntent().getStringExtra("Username");
        password = getIntent().getStringExtra("Password");

        playButton = findViewById(R.id.playButton);
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String videoLink = link.getText().toString();
                if (!videoLink.isEmpty()){
                    Intent intent = new Intent(Homepage.this, VideoPlayer.class);
                    intent.putExtra("Link", videoLink);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(Homepage.this, "Link cannot be empty", Toast.LENGTH_SHORT).show();
                }
            }
        });

        addButton = findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String videoLink = link.getText().toString();
                if (!videoLink.isEmpty()){
                    MyDatabase myDB = new MyDatabase(Homepage.this);
                    if (myDB.getPlaylists(username) != null){
                        playlists = myDB.getPlaylists(username);
                    }
                    playlists.add(videoLink);
                    myDB.addPlaylists(username, password, playlists);
                }
                else {
                    Toast.makeText(Homepage.this, "Link cannot be empty", Toast.LENGTH_SHORT).show();
                }

            }
        });

        playlistButton = findViewById(R.id.playlistButton);
        playlistButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Homepage.this, Playlists.class);
                intent.putExtra("Username", username);
                startActivity(intent);
            }
        });
    }
}