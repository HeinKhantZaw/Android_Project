package com.heinkhantzaw.tn.movie_application;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import com.github.paolorotolo.appintro.AppIntro2;
import com.github.paolorotolo.appintro.AppIntroFragment;
import com.github.paolorotolo.appintro.ISlideBackgroundColorHolder;
import androidx.constraintlayout.widget.ConstraintLayout;


public class IntroActivity extends AppIntro2 implements ISlideBackgroundColorHolder {
ConstraintLayout constraintLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addSlide(AppIntroFragment.newInstance("Explore", "the newest movie previews in your hands.", R.drawable.intro3, getResources().getColor(R.color.intro1)));
        addSlide(AppIntroFragment.newInstance("Browse", "the most authentic trailers and news. ", R.drawable.intro2, getResources().getColor(R.color.intro2)));
        addSlide(AppIntroFragment.newInstance("Ready!", "Let's get started!", R.drawable.intro1, getResources().getColor(R.color.intro3)));
        constraintLayout=findViewById(R.id.intro);
        setNavBarColor(R.color.colorAccent);
        showSkipButton(false);
        showStatusBar(true);
        setDepthAnimation();
    }

    private void loadMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void onDonePressed() {
        loadMainActivity();
    }

    @Override
    public int getDefaultBackgroundColor() {
        return Color.parseColor("#000000");
    }

    @Override
    public void setBackgroundColor(int backgroundColor) {
        if (  constraintLayout!= null) {
            constraintLayout.setBackgroundColor(backgroundColor);
        }
    }
}
