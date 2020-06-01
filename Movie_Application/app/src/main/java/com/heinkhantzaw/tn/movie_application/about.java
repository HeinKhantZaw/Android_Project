package com.heinkhantzaw.tn.movie_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class about extends AppCompatActivity {

    ImageView imgGithub,imgFacebook,imgInsta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        imgFacebook=findViewById(R.id.facebook);
        imgGithub=findViewById(R.id.github);
        imgInsta=findViewById(R.id.instagram);

        imgFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent("android.intent.action.VIEW", Uri.parse("https://www.facebook.com/hein.zaw.9028"));
                startActivity(intent);
            }
        });

        imgGithub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent("android.intent.action.VIEW", Uri.parse("https://github.com/HeinKhantZaw"));
                startActivity(intent);
            }
        });

        imgInsta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent("android.intent.action.VIEW", Uri.parse("https://www.instagram.com/heinzaw1"));
                startActivity(intent);
            }
        });
    }
}
