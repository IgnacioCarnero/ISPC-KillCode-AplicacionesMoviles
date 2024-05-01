package com.example.food_app;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.food_app.database.AppDataBase;
import com.example.food_app.database.entity.mesaEntity;

import java.util.ArrayList;
import java.util.List;

public class ConfigurarMesaActivity extends AppCompatActivity {

    private EditText mesaIdEditText;
    private Button agregarBtn;
    private Button eliminarBtn;
    private Button consultarBtn;
    private Spinner mesaSpinner;
    private AppDataBase appDataBase;
    private ArrayAdapter<Integer> mesaAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configurar_mesa);

        // Inicializar elementos de UI
        mesaIdEditText = findViewById(R.id.mesaIdEditText);
        agregarBtn = findViewById(R.id.agregarBtn);
        eliminarBtn = findViewById(R.id.eliminarBtn);
        consultarBtn = findViewById(R.id.consultarBtn);
        mesaSpinner = findViewById(R.id.mesaSpinner);

        // Inicializar la base de datos
        appDataBase = AppDataBase.getInstance(getApplicationContext());

        // Configurar el Spinner con las mesas existentes
        actualizarSpinnerMesas();

        // Configurar el botón de agregar
        agregarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                agregarMesa();
            }
        });

        // Configurar el botón de eliminar
        eliminarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eliminarMesa();
            }
        });

        // Configurar el botón de consultar
        consultarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                consultarMesa();
            }
        });
    }

    // Método para agregar una mesa a la base de datos
    private void agregarMesa() {
        // Obtener el ID de la mesa ingresado
        String mesaIdStr = mesaIdEditText.getText().toString();
        if (!mesaIdStr.isEmpty()) {
            int mesaId = Integer.parseInt(mesaIdStr);

            // Crear una nueva entidad de mesa y agregarla a la base de datos
            mesaEntity nuevaMesa = new mesaEntity();
            nuevaMesa.setId_mesa(mesaId);
            appDataBase.mesaDAO().insertMesa(nuevaMesa);

            // Mostrar un mensaje de éxito
            Toast.makeText(ConfigurarMesaActivity.this, "Mesa agregada", Toast.LENGTH_SHORT).show();

            // Actualizar el Spinner
            actualizarSpinnerMesas();

            // Limpiar el campo de texto
            mesaIdEditText.setText("");
        } else {
            Toast.makeText(ConfigurarMesaActivity.this, "Por favor, ingresa un ID de mesa válido", Toast.LENGTH_SHORT).show();
        }
    }

    // Método para eliminar una mesa de la base de datos
    private void eliminarMesa() {
        // Obtener el ID de la mesa seleccionada en el Spinner
        Integer mesaId = (Integer) mesaSpinner.getSelectedItem();
        if (mesaId != null) {
            // Obtener la entidad de mesa correspondiente al ID
            mesaEntity mesaAEliminar = new mesaEntity();
            mesaAEliminar.setId_mesa(mesaId);

            // Eliminar la mesa de la base de datos
            appDataBase.mesaDAO().deleteMesa(mesaAEliminar);

            // Mostrar un mensaje de éxito
            Toast.makeText(ConfigurarMesaActivity.this, "Mesa eliminada", Toast.LENGTH_SHORT).show();

            // Actualizar el Spinner
            actualizarSpinnerMesas();
        } else {
            Toast.makeText(ConfigurarMesaActivity.this, "Selecciona una mesa para eliminar", Toast.LENGTH_SHORT).show();
        }
    }

    // Método para consultar una mesa en la base de datos
    private void consultarMesa() {
        // Obtener el ID de la mesa ingresado
        String mesaIdStr = mesaIdEditText.getText().toString();
        if (!mesaIdStr.isEmpty()) {
            int mesaId = Integer.parseInt(mesaIdStr);

            // Consultar la mesa en la base de datos
            int idMesa = appDataBase.mesaDAO().getId_mesa(mesaId);

            // Verificar si la mesa existe
            if (idMesa != 0) {
                // Mostrar un mensaje con el resultado de la consulta
                Toast.makeText(ConfigurarMesaActivity.this, "Mesa encontrada: ID " + idMesa, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(ConfigurarMesaActivity.this, "Mesa no encontrada en la base de datos", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(ConfigurarMesaActivity.this, "Por favor, ingresa un ID de mesa válido", Toast.LENGTH_SHORT).show();
        }
    }

    // Método para actualizar el Spinner con las mesas existentes
    private void actualizarSpinnerMesas() {
        List<mesaEntity> mesas = appDataBase.mesaDAO().getId_mesa();
        List<Integer> mesaIds = new ArrayList<>();

        // Llenar la lista con los IDs de las mesas
        for (mesaEntity mesa : mesas) {
            mesaIds.add(mesa.getId_mesa());
        }

        // Configurar el Adapter para el Spinner
        mesaAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, mesaIds);
        mesaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mesaSpinner.setAdapter(mesaAdapter);
    }
}
