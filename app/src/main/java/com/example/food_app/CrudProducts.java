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
import com.example.food_app.database.entity.comidaBebida;
import java.math.BigDecimal;

public class CrudProducts extends AppCompatActivity {

    private EditText nombreEditText;
    private Spinner tipoSpinner;
    private EditText precioEditText;
    private EditText descripcionEditText;
    private Spinner categoriaSpinner;
    private Button saveBtn;
    private AppDataBase appDataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crud_products);

        // Inicializar elementos de UI
        nombreEditText = findViewById(R.id.nombreEditText);
        tipoSpinner = findViewById(R.id.tipoSpinner);
        precioEditText = findViewById(R.id.precioEditText);
        descripcionEditText = findViewById(R.id.descripcionEditText);
        categoriaSpinner = findViewById(R.id.categoriaSpinner);
        saveBtn = findViewById(R.id.saveProduct);

        appDataBase = AppDataBase.getInstance(getApplicationContext());

        // Configurar opciones para el Spinner de Categorías
        ArrayAdapter<CharSequence> categoriaAdapter = ArrayAdapter.createFromResource(
                this,
                R.array.categorias_array, // Debes definir este array en tus recursos
                android.R.layout.simple_spinner_item
        );
        categoriaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categoriaSpinner.setAdapter(categoriaAdapter);

        // Configurar opciones para el Spinner de Tipo
        ArrayAdapter<CharSequence> tipoAdapter = ArrayAdapter.createFromResource(
                this,
                R.array.tipos_array, // Debes definir este array en tus recursos
                android.R.layout.simple_spinner_item
        );
        tipoAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tipoSpinner.setAdapter(tipoAdapter);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Obtener los valores ingresados por el usuario
                String nombre = nombreEditText.getText().toString();
                String tipoComida = tipoSpinner.getSelectedItem().toString();
                BigDecimal precio = new BigDecimal(precioEditText.getText().toString());
                String descripcion = descripcionEditText.getText().toString();
                String categoria = categoriaSpinner.getSelectedItem().toString();

                int idCategoria = mapCategoriaToId(categoria);
                String tipo = mapTipoToId(tipoComida);
                comidaBebida nuevoProducto = new comidaBebida(nombre, tipo, precio, descripcion, idCategoria);

                appDataBase.comidaBebidaDAO().insertComidaBebida(nuevoProducto);

                // Limpia los campos después de guardar
                nombreEditText.setText("");
                tipoSpinner.setSelection(0);
                precioEditText.setText("");
                descripcionEditText.setText("");

                // Notifica que el producto ha sido insertado
                Toast.makeText(CrudProducts.this, "Producto guardado", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Esta función mapea las categorías a números (puedes personalizarla)
    private int mapCategoriaToId(String categoria) {
        switch (categoria) {
            case "Entrada":
                return 1;
            case "Plato principal":
                return 2;
            case "Postre":
                return 3;
            case "Bebida":
                return 4;
            default:
                // Maneja el caso si no se selecciona una categoría válida
                return 0; // o cualquier otro valor adecuado
        }
    }

    private String mapTipoToId(String tipo) {
        switch (tipo) {
            case "Vegetariana":
                return "Vegetariana";
            case "Calórica":
                return "Calórica";
            case "Ligth":
                return "Ligth";
        }
        return tipo;
    }
}
