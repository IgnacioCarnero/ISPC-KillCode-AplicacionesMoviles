package com.example.food_app.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.food_app.database.dao.CategoriaDAO;
import com.example.food_app.database.entity.categoriaEntity;

import java.util.ArrayList;
import java.util.List;

// Adaptador personalizado para mostrar solo el nombre de la categoría
public class CategoriaAdapter extends ArrayAdapter<categoriaEntity> {

    private List<categoriaEntity> categorias;
    public CategoriaAdapter(Context context, List<categoriaEntity> categorias) {
        super(context, android.R.layout.simple_spinner_item, categorias);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Utiliza el diseño estándar del spinner
        View view = super.getView(position, convertView, parent);
        // Obtén el nombre de la categoría actual
        categoriaEntity categoria = getItem(position);
        if (categoria != null) {
            // Establece el texto del TextView del spinner como el nombre de la categoría
            ((TextView) view).setText(categoria.getNombre());
        }
        return view;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        // Utiliza el diseño estándar de la lista desplegable del spinner
        View view = super.getDropDownView(position, convertView, parent);
        // Obtén el nombre de la categoría actual
        categoriaEntity categoria = getItem(position);
        if (categoria != null) {
            // Establece el texto del TextView del dropdown como el nombre de la categoría
            ((TextView) view).setText(categoria.getNombre());
        }
        return view;
    }

    // Nuevo método para recuperar las categorías de la base de datos
    public static List<categoriaEntity> getCategoriasFromDB(CategoriaDAO categoriaDAO) {
        return categoriaDAO.getAllCategorias();
    }

    public void updateCategorias(List<categoriaEntity> newCategorias) {
        this.categorias.clear();
        this.categorias.addAll(newCategorias);
        notifyDataSetChanged();
    }
}
