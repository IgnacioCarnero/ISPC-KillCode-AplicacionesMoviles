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


public class fragment1 extends Fragment {
    private List<ListElement> elements;
    private RecyclerView recyclerView;
    private ListAdapter listAdapter;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment1,container,false);
        recyclerView = view.findViewById(R.id.recycler1);
        init();

        return view;

    }


    public void init() {
        elements = new ArrayList<>();
        elements.add(new ListElement("Entrada 1", "", ""));
        elements.add(new ListElement("Entrada 2", "", ""));
        elements.add(new ListElement("Entrada 3", "", ""));
        elements.add(new ListElement("Entrada 4", "", ""));

        ListAdapter listAdapter = new ListAdapter(elements, requireContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerView.setAdapter(listAdapter);

    }
}