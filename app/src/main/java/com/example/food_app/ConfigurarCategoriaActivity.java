package com.example.food_app;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.food_app.adapter.CategoriaAdapter;
import com.example.food_app.database.AppDataBase;
import com.example.food_app.database.entity.categoriaEntity;

import java.util.ArrayList;
import java.util.List;

public class ConfigurarCategoriaActivity extends AppCompatActivity {

    private EditText nombreCategoriaEditText;
    private Button agregarBtn;
    private Button eliminarBtn;
    private Button actualizarBtn;
    private Spinner categoriaSpinner;
    private AppDataBase appDataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configurar_categoria);

        // Inicializar elementos de UI
        nombreCategoriaEditText = findViewById(R.id.nombreCategoriaEditText);
        agregarBtn = findViewById(R.id.agregarBtn);
        eliminarBtn = findViewById(R.id.eliminarBtn);
        actualizarBtn = findViewById(R.id.actualizarBtn);
        categoriaSpinner = findViewById(R.id.categoriaSpinner);

        // Inicializar la base de datos
        appDataBase = AppDataBase.getInstance(getApplicationContext());

        // Configurar el Spinner con las categorías existentes
        actualizarSpinnerCategorias();

        // Configurar el botón de agregar
        agregarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                agregarCategoria();
            }
        });

        // Configurar el botón de eliminar
        eliminarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eliminarCategoria();
            }
        });

        // Configurar el botón de actualizar
        actualizarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actualizarCategoria();
            }
        });

        // Configurar el Spinner para manejar la selección de una categoría
        categoriaSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Obtener la categoría seleccionada
                categoriaEntity categoriaSeleccionada = (categoriaEntity) parent.getSelectedItem();

                // Rellenar el EditText con el nombre de la categoría seleccionada
                nombreCategoriaEditText.setText(categoriaSeleccionada.getNombre());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // No hacer nada cuando no se selecciona nada
            }
        });
    }

    // Método para agregar una nueva categoría a la base de datos
    private void agregarCategoria() {
        String nombre = nombreCategoriaEditText.getText().toString();
        if (!nombre.isEmpty()) {
            categoriaEntity nuevaCategoria = new categoriaEntity(nombre);
            appDataBase.categoriaDAO().insertCategoria(nuevaCategoria);
            Toast.makeText(this, "Categoría agregada", Toast.LENGTH_SHORT).show();
            actualizarSpinnerCategorias();
            nombreCategoriaEditText.setText(""); // Limpiar el campo de entrada
        } else {
            Toast.makeText(this, "Por favor, ingresa un nombre de categoría válido", Toast.LENGTH_SHORT).show();
        }
    }

    // Método para eliminar una categoría de la base de datos
    private void eliminarCategoria() {
        categoriaEntity categoriaSeleccionada = (categoriaEntity) categoriaSpinner.getSelectedItem();
        if (categoriaSeleccionada != null) {
            appDataBase.categoriaDAO().deleteCategoria(categoriaSeleccionada);
            Toast.makeText(this, "Categoría eliminada", Toast.LENGTH_SHORT).show();
            actualizarSpinnerCategorias();
        } else {
            Toast.makeText(this, "Por favor, selecciona una categoría para eliminar", Toast.LENGTH_SHORT).show();
        }
    }

    // Método para actualizar el nombre de una categoría en la base de datos
    private void actualizarCategoria() {
        categoriaEntity categoriaSeleccionada = (categoriaEntity) categoriaSpinner.getSelectedItem();
        if (categoriaSeleccionada != null) {
            String nuevoNombre = nombreCategoriaEditText.getText().toString();
            if (!nuevoNombre.isEmpty()) {
                categoriaSeleccionada.setNombre(nuevoNombre);
                appDataBase.categoriaDAO().updateCategoria(categoriaSeleccionada);
                Toast.makeText(this, "Categoría actualizada", Toast.LENGTH_SHORT).show();
                actualizarSpinnerCategorias();
            } else {
                Toast.makeText(this, "Por favor, ingresa un nombre de categoría válido", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Por favor, selecciona una categoría para actualizar", Toast.LENGTH_SHORT).show();
        }
    }

    // Método para actualizar el Spinner con las categorías existentes
    private void actualizarSpinnerCategorias() {
        List<categoriaEntity> categorias = appDataBase.categoriaDAO().getAllCategorias();

        // Crea una nueva lista para agregar la opción "Seleccionar" al principio
        List<categoriaEntity> categoriasConSeleccionar = new ArrayList<>();

        // Agrega una categoría ficticia con nombre "Seleccionar"
        categoriasConSeleccionar.add(new categoriaEntity(""));

        // Agrega las categorías reales a la lista después de la opción "Seleccionar"
        categoriasConSeleccionar.addAll(categorias);

        // Utiliza un adaptador personalizado para el Spinner
        CategoriaAdapter categoriaAdapter = new CategoriaAdapter(this, categoriasConSeleccionar);
        categoriaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categoriaSpinner.setAdapter(categoriaAdapter);
    }

    // Método para configurar el listener del Spinner
    private void configurarSpinnerListener() {
        categoriaSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Verifica si la opción seleccionada es la opción predeterminada "Seleccionar"
                if (position == 0) {
                    // La opción "Seleccionar" está seleccionada, no actualiza el EditText
                    nombreCategoriaEditText.setText("");
                } else {
                    // Obtener la categoría seleccionada
                    categoriaEntity categoriaSeleccionada = (categoriaEntity) parent.getSelectedItem();

                    // Rellenar el EditText con el nombre de la categoría seleccionada
                    nombreCategoriaEditText.setText(categoriaSeleccionada.getNombre());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // No hacer nada cuando no se selecciona nada
            }
        });
    }
}
