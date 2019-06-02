package com.heinkhantzaw.tn.movie_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.github.ybq.android.spinkit.SpinKitView;
import com.yarolegovich.discretescrollview.DiscreteScrollView;

public class MainActivity extends AppCompatActivity {
SpinKitView loading;
DiscreteScrollView dis;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showIntroOnce();
        loading=findViewById(R.id.spin_kit);
        dis=findViewById(R.id.picker);
    }


    private void showLoadingView()
    {
        loading.setVisibility(View.VISIBLE);
        dis.setVisibility(View.GONE);
    }
    private void showNormalView()
    {
        loading.setVisibility(View.GONE);
        dis.setVisibility(View.VISIBLE);
    }

    public void showIntroOnce() {
        SharedPreferences sp = getSharedPreferences("Main", Context.MODE_PRIVATE);
        if (!sp.getBoolean("first", false)) {
            SharedPreferences.Editor editor = sp.edit();
            editor.putBoolean("first", true);
            editor.apply();
            Intent intent = new Intent(this, IntroActivity.class); // Call the AppIntro java class
            startActivity(intent);
        }
    }
}
