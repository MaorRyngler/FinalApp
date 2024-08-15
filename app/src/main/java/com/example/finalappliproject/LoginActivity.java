package com.example.finalappliproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.example.finalappliproject.Adapters.LoginAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager2 viewPager;
    LoginAdapter adapter;
    FloatingActionButton fb,google;

    private String[] titles =  new String[]{"Login","SignUp"};

    float v = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        findViews();

        tabLayout.addTab(tabLayout.newTab().setText("Login"));
        tabLayout.addTab(tabLayout.newTab().setText("Sign Up"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        adapter = new LoginAdapter(this);
        viewPager.setAdapter(adapter);
        new TabLayoutMediator(tabLayout,viewPager,((tab, position) -> tab.setText(titles[position]))).attach();

        animation();
    }


    private void findViews(){
        tabLayout = findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.view_pager);
//        fb = findViewById(R.id.fab_fb);
        google = findViewById(R.id.fab_google);
    }

    private void animation(){
//        fb.setTranslationY(300);
        tabLayout.setTranslationY(300);
        google.setTranslationY(300);

//        fb.setAlpha(v);
        tabLayout.setAlpha(v);
        google.setAlpha(v);

//        fb.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(400).start();
        tabLayout.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(100).start();
        google.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(600).start();

    }
}