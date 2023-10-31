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
import com.example.food_app.database.AppDataBase;
import com.example.food_app.database.entity.comidaBebida;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CrudProducts extends AppCompatActivity {

    private EditText nombreEditText;
    private Spinner tipoSpinner;
    private EditText precioEditText;
    private EditText descripcionEditText;
    private Spinner categoriaSpinner;
    private Button saveBtn;
    private Button deleteBtn;
    private Spinner productSpinner;
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

        // Agregar referencias para el botón de eliminar y el Spinner de productos
        deleteBtn = findViewById(R.id.deleteProduct);
        productSpinner = findViewById(R.id.productSpinner);

        // Configurar el Spinner para mostrar solo los nombres de productos
        final List<comidaBebida> productos = appDataBase.comidaBebidaDAO().getId_comidaBebida();
        List<String> productNames = new ArrayList<>();

        for (comidaBebida producto : productos) {
            productNames.add(producto.getNombre());
        }

        ArrayAdapter<String> productAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, productNames);
        productAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        productSpinner.setAdapter(productAdapter);

        productSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // Cuando se selecciona un producto, obtén el producto seleccionado desde la lista
                String productName = productSpinner.getSelectedItem().toString();
                comidaBebida productoSeleccionado = obtenerProductoPorNombre(productName);

                if (productoSeleccionado != null) {
                    // Llena el formulario con los datos del producto seleccionado
                    nombreEditText.setText(productoSeleccionado.getNombre());
                    tipoSpinner.setSelection(mapTipoToPosition(productoSeleccionado.getTipo()));
                    precioEditText.setText(productoSeleccionado.getPrecio().toString());
                    descripcionEditText.setText(productoSeleccionado.getDescripcion());
                    categoriaSpinner.setSelection(mapCategoriaToPosition(productoSeleccionado.getId_categoria()));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Maneja el caso en el que no se ha seleccionado ningún producto
            }
        });


        // Configurar un escuchador para el botón de eliminación
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Obtener el nombre del producto seleccionado en el Spinner
                String productName = productSpinner.getSelectedItem().toString();

                if (!productName.isEmpty()) {
                    // Eliminar el producto de la base de datos
                    for (comidaBebida producto : productos) {
                        if (producto.getNombre().equals(productName)) {
                            appDataBase.comidaBebidaDAO().deleteComidaBebida(producto);
                            break;
                        }
                    }

                    // Actualizar la lista de productos en el Spinner
                    productNames.remove(productName);
                    productAdapter.notifyDataSetChanged();

                    // Notificar que el producto ha sido eliminado
                    Toast.makeText(CrudProducts.this, "Producto eliminado", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(CrudProducts.this, "Selecciona un producto para eliminar", Toast.LENGTH_SHORT).show();
                }
            }
        });
        Button updateBtn = findViewById(R.id.updateProduct);
        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actualizarProductoSeleccionado();
            }
        });
    }

    private void actualizarProductoSeleccionado() {
        String productName = productSpinner.getSelectedItem().toString();
        comidaBebida productoSeleccionado = obtenerProductoPorNombre(productName);

        if (productoSeleccionado != null) {
            // Obtén los valores actualizados del formulario
            String nuevoNombre = nombreEditText.getText().toString();
            String nuevoTipo = tipoSpinner.getSelectedItem().toString();
            BigDecimal nuevoPrecio = new BigDecimal(precioEditText.getText().toString());
            String nuevaDescripcion = descripcionEditText.getText().toString();
            int nuevaIdCategoria = mapCategoriaToId(categoriaSpinner.getSelectedItem().toString());

            // Actualiza los datos del producto seleccionado
            productoSeleccionado.setNombre(nuevoNombre);
            productoSeleccionado.setTipo(nuevoTipo);
            productoSeleccionado.setPrecio(nuevoPrecio);
            productoSeleccionado.setDescripcion(nuevaDescripcion);
            productoSeleccionado.setId_categoria(nuevaIdCategoria);

            // Actualiza el producto en la base de datos
            appDataBase.comidaBebidaDAO().updateComidaBebida(productoSeleccionado);

            // Notifica que el producto ha sido actualizado
            Toast.makeText(CrudProducts.this, "Producto actualizado", Toast.LENGTH_SHORT).show();

            // Actualiza el Spinner de productos
            actualizarSpinnerProductos();
        } else {
            Toast.makeText(CrudProducts.this, "Producto no encontrado", Toast.LENGTH_SHORT).show();
        }
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
    private int mapTipoToPosition(String tipo) {
        ArrayAdapter<CharSequence> tipoAdapter = (ArrayAdapter<CharSequence>) tipoSpinner.getAdapter();
        return tipoAdapter.getPosition(tipo);
    }
    private int mapCategoriaToPosition(int idCategoria) {
        // Solo existen tres categorías con IDs 1, 2 y 3
        if (idCategoria >= 1 && idCategoria <= 3) {
            return idCategoria - 1; // Restar 1 para obtener la posición correcta
        } else {

            return 0;
        }
    }
    private comidaBebida obtenerProductoPorNombre(String nombre) {
        List<comidaBebida> productos = appDataBase.comidaBebidaDAO().getId_comidaBebida();
        for (comidaBebida producto : productos) {
            if (producto.getNombre().equals(nombre)) {
                return producto;
            }
        }
        return null; // Retorna nulo si no se encuentra el producto
    }
    private void actualizarSpinnerProductos() {
        final List<comidaBebida> productos = appDataBase.comidaBebidaDAO().getId_comidaBebida();
        List<String> productNames = new ArrayList<>();

        for (comidaBebida producto : productos) {
            productNames.add(producto.getNombre());
        }

        ArrayAdapter<String> productAdapter = (ArrayAdapter<String>) productSpinner.getAdapter();
        productAdapter.clear();
        productAdapter.addAll(productNames);
        productAdapter.notifyDataSetChanged();
    }
}
