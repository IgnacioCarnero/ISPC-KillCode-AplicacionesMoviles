package com.example.food_app.menu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.food_app.ListElement;
import com.example.food_app.R;
import com.example.food_app.ToConfirmOrderActivity;
import com.example.food_app.adapter.CategoriaAdapter;
import com.example.food_app.adapter.VPAdapter;
import com.example.food_app.database.AppDataBase;
import com.example.food_app.database.dao.CategoriaDAO;
import com.example.food_app.database.entity.categoriaEntity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.List;

public class MenuScroll extends AppCompatActivity {
    List<ListElement> elements;
    TabLayout tabLayout;
    ViewPager2 viewPager2;
    VPAdapter vpAdapter;
    FloatingActionButton btnOrder;

    private AppDataBase appDataBase;
    private CategoriaDAO categoriaDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_scroll);

        tabLayout = findViewById(R.id.tabLayout);
        viewPager2 = findViewById(R.id.viewpager);
        btnOrder = findViewById(R.id.btnOrder);

        appDataBase = AppDataBase.getInstance(this);
        categoriaDAO = appDataBase.categoriaDAO();

        // Recupera las categorías de la base de datos usando el nuevo método del adaptador
        List<categoriaEntity> categorias = CategoriaAdapter.getCategoriasFromDB(categoriaDAO);

        vpAdapter = new VPAdapter(this, categorias);
        viewPager2.setAdapter(vpAdapter);

        new TabLayoutMediator(tabLayout, viewPager2, (tab, position) -> {
            tab.setText(vpAdapter.getPageTitle(position));
        }).attach();

        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent btnOrder = new Intent(MenuScroll.this, ToConfirmOrderActivity.class);
                startActivity(btnOrder);
            }
        });
    }
}