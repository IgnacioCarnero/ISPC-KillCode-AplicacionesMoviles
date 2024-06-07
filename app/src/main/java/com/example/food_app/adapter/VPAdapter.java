package com.example.food_app.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.food_app.fragmentsMenu.PrincipalFragment;
import com.example.food_app.database.entity.categoriaEntity;

import java.util.List;


public class VPAdapter extends FragmentStateAdapter {

    private List<categoriaEntity> categorias;
    public VPAdapter(@NonNull FragmentActivity fragmentActivity, List<categoriaEntity> categorias) {
        super(fragmentActivity);
        this.categorias = categorias;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        int categoryId = categorias.get(position).getId_categoria();
        return PrincipalFragment.newInstance(categoryId); // Asegúrate de que el fragmento acepte esta nueva instancia
    }

    @Override
    public int getItemCount() {
        return categorias.size();
    }

    // Método para obtener el título de la página (nombre de la categoría)
    public String getPageTitle(int position) {
        return categorias.get(position).getNombre();
    }

}
