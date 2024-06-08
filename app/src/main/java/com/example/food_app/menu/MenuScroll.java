package com.example.food_app.menu;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.food_app.ListElement;
import com.example.food_app.R;
import com.example.food_app.adapter.CategoriaAdapter;
import com.example.food_app.adapter.ListAdapter;
import com.example.food_app.adapter.VPAdapter;
import com.example.food_app.database.AppDataBase;
import com.example.food_app.database.dao.CategoriaDAO;
import com.example.food_app.database.entity.categoriaEntity;

import com.example.food_app.database.entity.comidaBebida;
import com.example.food_app.database.utility.OrderUtils;

import com.example.food_app.waiterActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MenuScroll extends AppCompatActivity  implements ListAdapter.OnQuantityChangeListener {
    List<ListElement> elements;
    TabLayout tabLayout;
    ViewPager2 viewPager2;
    VPAdapter vpAdapter;
    FloatingActionButton btnOrder;
    FloatingActionButton btnWaiter;

    private AppDataBase appDataBase;
    private CategoriaDAO categoriaDAO;

    private List<comidaBebida> productosEnPedido = new ArrayList<>();
    private HashMap<Integer, Integer> cantidadesPorProducto = new HashMap<>();
    private ArrayList<comidaBebida> selectedItems = new ArrayList<>();

    // Implementación del método de la interfaz OnItemQuantityChangedListener
    @Override
    public void onQuantityChanged(comidaBebida comida) {
        int cantidad = OrderUtils.getCantidad(comida, cantidadesPorProducto);
        if (cantidad > 0) {
            // Si la cantidad es mayor a 0, añade el producto a la lista
            if (!productosEnPedido.contains(comida)) {
                productosEnPedido.add(comida);
            }
            // Actualiza la cantidad en el mapa
            OrderUtils.actualizarCantidad(comida, cantidad, cantidadesPorProducto);
        } else {
            // Si la cantidad es 0, elimina el producto de la lista
            productosEnPedido.remove(comida);
            // Asegúrate de actualizar el mapa
            cantidadesPorProducto.remove(comida.getId_comidaBebida());
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_scroll);
        selectedItems = new ArrayList<>();
        tabLayout = findViewById(R.id.tabLayout);
        viewPager2 = findViewById(R.id.viewpager);
        btnOrder = findViewById(R.id.btnOrder);
        btnWaiter = findViewById(R.id.btn_waiter);

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
                Log.d("MenuScroll", "productosEnPedido: " + productosEnPedido);
                if (productosEnPedido != null && !productosEnPedido.isEmpty()) {
                    ArrayList<comidaBebida> selectedItemsList = new ArrayList<>(productosEnPedido);
                    Intent intent = new Intent(MenuScroll.this, OrderConfirmationActivity.class);
                    intent.putParcelableArrayListExtra("productos", selectedItemsList);
                    startActivity(intent);
                } else {
                    Toast.makeText(MenuScroll.this, "No items selected", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnWaiter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent btnWaiter = new Intent(MenuScroll.this, waiterActivity.class);
                startActivity(btnWaiter);
            }
        });
    }
    @Override
    public void onQuantityChanged(comidaBebida comida, int newQuantity) {
        if (newQuantity > 0) {
            // Añade el producto a la lista si no está ya
            if (!productosEnPedido.contains(comida)) {
                productosEnPedido.add(comida);
            }
            // Actualiza la cantidad en el mapa
            OrderUtils.actualizarCantidad(comida, newQuantity, cantidadesPorProducto);
        } else {
            // Elimina el producto de la lista si la cantidad es 0
            productosEnPedido.remove(comida);
            cantidadesPorProducto.remove(comida.getId_comidaBebida());
        }
    }
}