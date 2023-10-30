package com.example.food_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.food_app.adapter.VPAdapter;
import com.google.android.material.tabs.TabLayout;

import java.util.List;

public class MenuScroll extends AppCompatActivity {
    List<ListElement> elements;
    TabLayout tabLayout;
    ViewPager2 viewPager2;
    VPAdapter vpAdapter;
    Button btnOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_scroll);

        tabLayout = findViewById(R.id.tabLayout);
        viewPager2 = findViewById(R.id.viewpager);
        btnOrder = findViewById(R.id.btnOrder);

        vpAdapter = new VPAdapter(this);
        viewPager2.setAdapter(vpAdapter);


        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent btnOrder= new Intent( MenuScroll.this, ToConfirmOrderActivity.class);
                startActivity(btnOrder);
            }
        });


        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                tabLayout.getTabAt(position).select();
            }

        });
    }




}