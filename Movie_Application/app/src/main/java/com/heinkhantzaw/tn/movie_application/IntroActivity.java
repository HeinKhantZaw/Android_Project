package com.heinkhantzaw.tn.movie_application;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.github.paolorotolo.appintro.AppIntro2;
import com.github.paolorotolo.appintro.AppIntroFragment;
import com.github.paolorotolo.appintro.ISlideBackgroundColorHolder;

public class IntroActivity extends AppIntro2 implements ISlideBackgroundColorHolder {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addSlide(AppIntroFragment.newInstance("Explore", "the newest movie previews in your hands.", R.drawable.ic_launcher_background, getResources().getColor(R.color.colorAccent)));
        addSlide(AppIntroFragment.newInstance("Browse", "the most authentic trailers and news. ", R.drawable.ic_launcher_background, getResources().getColor(R.color.design_default_color_primary_variant)));
        addSlide(AppIntroFragment.newInstance("Read", "critics and reviews.", R.drawable.ic_launcher_background, getResources().getColor(R.color.colorPrimary)));
        setNavBarColor(R.color.colorAccent);
        setFadeAnimation();
        showSkipButton(false);
    }

    private void loadMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void onDonePressed() {
        loadMainActivity();
    }

    public void getStarted(View v) {
        loadMainActivity();
    }

    @Override
    public int getDefaultBackgroundColor() {
        return Color.parseColor("#000000");
    }

    @Override
    public void setBackgroundColor(int backgroundColor) {
        if (customBackgroundView != null) {
            customBackgroundView.setBackgroundColor(backgroundColor);
        }
    }
}
