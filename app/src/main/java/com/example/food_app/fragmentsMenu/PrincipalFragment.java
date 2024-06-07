package com.example.food_app.fragmentsMenu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.food_app.R;
import com.example.food_app.adapter.ListAdapter;
import com.example.food_app.database.AppDataBase;
import com.example.food_app.database.entity.comidaBebida;
import java.util.List;

public class PrincipalFragment extends Fragment {
    private RecyclerView recyclerView;
    private ListAdapter listAdapter;
    private AppDataBase appDataBase;

    private static final String ARG_CATEGORY_ID = "category_id";
    private int categoryId;

        public static PrincipalFragment newInstance(int categoryId) {
            PrincipalFragment fragment = new PrincipalFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_CATEGORY_ID, categoryId);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            if (getArguments() != null) {
                categoryId = getArguments().getInt(ARG_CATEGORY_ID);
            }
        }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.principal_fragment, container, false);
        recyclerView = view.findViewById(R.id.recycler1);

        // Inicializa la base de datos
        appDataBase = AppDataBase.getInstance(requireContext());

        // Recupera los datos de la base de datos
        List<comidaBebida> comidaBebidaList = appDataBase.comidaBebidaDAO().getId_categoria(categoryId);

        // Inicializa el adaptador con los datos recuperados
        listAdapter = new ListAdapter(comidaBebidaList, requireContext());

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerView.setAdapter(listAdapter);
        return view;
    }
}
