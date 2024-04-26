package com.example.a51cp2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class VideoPlayer extends AppCompatActivity {

    WebView webview;
    String link;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_player);

        link = getIntent().getStringExtra("Link");
        link.substring(32);
        webview = findViewById(R.id.webView);
        WebSettings webSettings = webview.getSettings();
        webSettings.setJavaScriptEnabled(true);

        webview.loadDataWithBaseURL("https://www.youtube.com", getYouTubeHTML(link.substring(32)), "text/html", "utf-8", null);
    }

    private String getYouTubeHTML(String videoId) {
        return "<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/" + videoId + "?autoplay=1\" frameborder=\"0\" allowfullscreen></iframe>";
    }
}