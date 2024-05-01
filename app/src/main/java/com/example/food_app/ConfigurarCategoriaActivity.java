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
                // Verificar si el elemento seleccionado es el placeholder ("Seleccionar")
                if (position == 0) {
                    // Si el placeholder está seleccionado, limpia el `EditText`
                    nombreCategoriaEditText.setText("");
                    return;
                }

                // Obtener la categoría seleccionada
                categoriaEntity categoriaSeleccionada = (categoriaEntity) parent.getSelectedItem();

                // Rellenar el `EditText` con el nombre de la categoría seleccionada
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
        // Obtener todas las categorías existentes en la base de datos
        List<categoriaEntity> categorias = appDataBase.categoriaDAO().getAllCategorias();

        // Crear una lista de categorías con un elemento adicional de placeholder
        List<categoriaEntity> categoriasConSeleccionar = new ArrayList<>();

        // Agregar una categoría ficticia con nombre "Seleccionar"
        categoriasConSeleccionar.add(new categoriaEntity("Seleccionar"));

        // Agregar las categorías reales a la lista después del placeholder
        categoriasConSeleccionar.addAll(categorias);

        // Configurar un adaptador personalizado para el Spinner de categorías
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
