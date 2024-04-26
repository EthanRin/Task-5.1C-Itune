package com.example.a51cp2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.List;

public class Playlists extends AppCompatActivity {

    List<String> linkPlaylist;
    String username;
    RecyclerView playlistView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playlists);

        MyDatabase myDB = new MyDatabase(Playlists.this);
        username = getIntent().getStringExtra("Username");
        linkPlaylist = myDB.getPlaylists(username);
        playlistView = findViewById(R.id.playlistView);
        CustomAdapter customAdapter = new CustomAdapter(this, linkPlaylist);
        playlistView.setAdapter(customAdapter);
        playlistView.setLayoutManager(new LinearLayoutManager(Playlists.this));
    }
}