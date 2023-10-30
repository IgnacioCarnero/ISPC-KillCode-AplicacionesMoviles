package com.example.food_app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.food_app.adapter.ListAdapter;
import com.example.food_app.database.AppDataBase;
import com.example.food_app.database.entity.comidaBebida;
import java.util.List;

public class fragment4 extends Fragment {
    private RecyclerView recyclerView;
    private ListAdapter listAdapter;
    private AppDataBase appDataBase;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragment1, container, false);
        recyclerView = view.findViewById(R.id.recycler1);

        // Inicializa la base de datos
        appDataBase = AppDataBase.getInstance(requireContext());

        // Recupera los datos de la base de datos
        List<comidaBebida> comidaBebidaList = appDataBase.comidaBebidaDAO().getId_categoria(4);

        // Inicializa el adaptador con los datos recuperados
        listAdapter = new ListAdapter(comidaBebidaList, requireContext());

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerView.setAdapter(listAdapter);
        return view;
    }
}
