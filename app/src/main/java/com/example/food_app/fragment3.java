package com.example.food_app;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.food_app.adapter.ListAdapter;

import java.util.ArrayList;
import java.util.List;


public class fragment3 extends Fragment {

    private List<ListElement> elements;
    private RecyclerView recyclerView;
    private ListAdapter listAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_fragment3,container,false);
        recyclerView = view.findViewById(R.id.recycler3);
        init();

        return view;

    }

    //Aca deberia recuperar los datos de la base de datos

    public void init() {
        elements = new ArrayList<>();
        elements.add(new ListElement("Bebidas1", "", ""));
        elements.add(new ListElement("Bebidas2", "", ""));
        elements.add(new ListElement("Bebidas3", "", ""));
        elements.add(new ListElement("Bebidas4", "", ""));

        ListAdapter listAdapter = new ListAdapter(elements, requireContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerView.setAdapter(listAdapter);

    }
}