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
import com.example.food_app.database.entity.categoriaEntity;
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
    private List<Integer> categoriaIds;

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
        deleteBtn = findViewById(R.id.deleteProduct);
        productSpinner = findViewById(R.id.productSpinner);

        // Inicializar la base de datos
        appDataBase = AppDataBase.getInstance(getApplicationContext());

        // Configurar el Spinner de categorías con las categorías existentes
        configurarSpinnerCategorias();

        // Configurar el Spinner de tipo con las opciones existentes
        configurarSpinnerTipo();

        // Configurar el Spinner de productos
        configurarSpinnerProductos();

        // Configurar el botón de guardar
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                guardarProducto();
            }
        });

        // Configurar el botón de eliminar
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eliminarProducto();
            }
        });

        // Configurar el botón de actualizar
        Button updateBtn = findViewById(R.id.updateProduct);
        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actualizarProductoSeleccionado();
            }
        });

        // Configurar el listener para el Spinner de productos
        productSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // Verificar si el elemento seleccionado es el placeholder
                if (position == 0) {
                    // Si el placeholder está seleccionado, no hacer nada
                    return;
                }

                // Cuando se selecciona un producto, obtener el producto seleccionado desde la lista
                String productName = productSpinner.getSelectedItem().toString();
                comidaBebida productoSeleccionado = obtenerProductoPorNombre(productName);

                if (productoSeleccionado != null) {
                    // Rellenar los campos del formulario con los datos del producto seleccionado
                    nombreEditText.setText(productoSeleccionado.getNombre());
                    int tipoPosition = mapTipoToPosition(productoSeleccionado.getTipo());
                    tipoSpinner.setSelection(tipoPosition);
                    precioEditText.setText(productoSeleccionado.getPrecio().toString());
                    descripcionEditText.setText(productoSeleccionado.getDescripcion());
                    categoriaSpinner.setSelection(mapCategoriaToPosition(productoSeleccionado.getId_categoria()));
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // No hacer nada cuando no se selecciona nada
            }
        });
    }

    // Método para configurar el Spinner de categorías con las categorías existentes
    private void configurarSpinnerCategorias() {
        // Obtener todas las categorías desde la base de datos
        List<categoriaEntity> categorias = appDataBase.categoriaDAO().getAllCategorias();

        // Crear una lista de nombres de categorías y una lista de IDs de categorías
        List<String> categoriaNombres = new ArrayList<>();
        categoriaIds = new ArrayList<>();

        for (categoriaEntity categoria : categorias) {
            categoriaNombres.add(categoria.getNombre());
            categoriaIds.add(categoria.getId_categoria());
        }

        // Configurar un adaptador personalizado para el Spinner de categorías
        ArrayAdapter<String> categoriaAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categoriaNombres);
        categoriaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categoriaSpinner.setAdapter(categoriaAdapter);
    }

    // Método para obtener la posición de una categoría en el Spinner de categorías
    private int mapCategoriaToPosition(int idCategoria) {
        // Busca la posición del ID de la categoría en la lista de IDs de categorías
        for (int i = 0; i < categoriaIds.size(); i++) {
            if (categoriaIds.get(i) == idCategoria) {
                // Retorna la posición correspondiente al ID de la categoría en el Spinner
                return i;
            }
        }
        // Si no se encuentra la categoría, retorna la posición por defecto (0)
        return 0;
    }

    // Método para obtener el ID de la categoría seleccionada en el Spinner de categorías
    private int obtenerIdCategoriaSeleccionada() {
        // Obtener la posición de la categoría seleccionada en el Spinner
        int selectedCategoryIndex = categoriaSpinner.getSelectedItemPosition();

        // Obtener el ID de la categoría correspondiente a la posición seleccionada
        return categoriaIds.get(selectedCategoryIndex);
    }

    // Método para configurar el Spinner de tipo con las opciones existentes
    private void configurarSpinnerTipo() {
        ArrayAdapter<CharSequence> tipoAdapter = ArrayAdapter.createFromResource(
                this,
                R.array.tipos_array, // Debes definir este array en tus recursos
                android.R.layout.simple_spinner_item
        );
        tipoAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tipoSpinner.setAdapter(tipoAdapter);
    }

    // Método para configurar el Spinner de productos con los productos existentes
    private void configurarSpinnerProductos() {
        // Obtener todos los productos existentes en la base de datos
        List<comidaBebida> productos = appDataBase.comidaBebidaDAO().getId_comidaBebida();
        List<String> productNames = new ArrayList<>();

        // Agregar un elemento placeholder al inicio de la lista de nombres de productos
        productNames.add("Seleccionar un producto");

        for (comidaBebida producto : productos) {
            productNames.add(producto.getNombre());
        }

        // Configurar un adaptador para el Spinner de productos
        ArrayAdapter<String> productAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, productNames);
        productAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        productSpinner.setAdapter(productAdapter);
    }

    // Método para guardar un producto en la base de datos
    private void guardarProducto() {
        // Obtener los valores ingresados por el usuario
        String nombre = nombreEditText.getText().toString();
        String tipoComida = tipoSpinner.getSelectedItem().toString();
        BigDecimal precio = new BigDecimal(precioEditText.getText().toString());
        String descripcion = descripcionEditText.getText().toString();

        // Obtener el ID de la categoría seleccionada en el Spinner
        int idCategoria = obtenerIdCategoriaSeleccionada();

        // Crear un nuevo producto con los valores ingresados y el ID de la categoría seleccionada
        comidaBebida nuevoProducto = new comidaBebida(nombre, tipoComida, precio, descripcion, idCategoria);

        // Insertar el nuevo producto en la base de datos
        appDataBase.comidaBebidaDAO().insertComidaBebida(nuevoProducto);

        // Limpia los campos después de guardar
        nombreEditText.setText("");
        tipoSpinner.setSelection(0);
        precioEditText.setText("");
        descripcionEditText.setText("");
        categoriaSpinner.setSelection(0);

        // Notifica que el producto ha sido guardado
        Toast.makeText(CrudProducts.this, "Producto guardado", Toast.LENGTH_SHORT).show();

        // Actualizar el Spinner de productos
        configurarSpinnerProductos();

    }

    // Método para eliminar un producto de la base de datos
    private void eliminarProducto() {
        // Obtener el nombre del producto seleccionado en el Spinner
        String productName = productSpinner.getSelectedItem().toString();

        if (!productName.isEmpty()) {
            // Obtener el producto seleccionado
            comidaBebida productoSeleccionado = obtenerProductoPorNombre(productName);

            if (productoSeleccionado != null) {
                // Eliminar el producto de la base de datos
                appDataBase.comidaBebidaDAO().deleteComidaBebida(productoSeleccionado);

                // Actualizar el Spinner de productos
                configurarSpinnerProductos();

                // Notificar que el producto ha sido eliminado
                Toast.makeText(CrudProducts.this, "Producto eliminado", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(CrudProducts.this, "Selecciona un producto para eliminar", Toast.LENGTH_SHORT).show();
        }
    }

    // Método para actualizar un producto seleccionado
    private void actualizarProductoSeleccionado() {
        // Obtener el nombre del producto seleccionado en el Spinner
        String productName = productSpinner.getSelectedItem().toString();
        comidaBebida productoSeleccionado = obtenerProductoPorNombre(productName);

        if (productoSeleccionado != null) {
            // Obtener los valores actualizados del formulario
            String nuevoNombre = nombreEditText.getText().toString();
            String nuevoTipo = tipoSpinner.getSelectedItem().toString();
            BigDecimal nuevoPrecio = new BigDecimal(precioEditText.getText().toString());
            String nuevaDescripcion = descripcionEditText.getText().toString();
            int nuevaIdCategoria = mapCategoriaToPosition(Integer.parseInt(categoriaSpinner.getSelectedItem().toString()));

            // Actualizar los datos del producto seleccionado
            productoSeleccionado.setNombre(nuevoNombre);
            productoSeleccionado.setTipo(nuevoTipo);
            productoSeleccionado.setPrecio(nuevoPrecio);
            productoSeleccionado.setDescripcion(nuevaDescripcion);
            productoSeleccionado.setId_categoria(nuevaIdCategoria);

            // Actualizar el producto en la base de datos
            appDataBase.comidaBebidaDAO().updateComidaBebida(productoSeleccionado);

            // Notificar que el producto ha sido actualizado
            Toast.makeText(CrudProducts.this, "Producto actualizado", Toast.LENGTH_SHORT).show();

            // Actualizar el Spinner de productos
            configurarSpinnerProductos();
        } else {
            Toast.makeText(CrudProducts.this, "Producto no encontrado", Toast.LENGTH_SHORT).show();
        }
    }

    // Método para obtener un producto por nombre
    private comidaBebida obtenerProductoPorNombre(String nombre) {
        List<comidaBebida> productos = appDataBase.comidaBebidaDAO().getId_comidaBebida();
        for (comidaBebida producto : productos) {
            if (producto.getNombre().equals(nombre)) {
                return producto;
            }
        }
        return null;
    }


    // Método para obtener la posición de un tipo en el Spinner de tipos
    private int mapTipoToPosition(String tipo) {
        // Obtener el adaptador del Spinner de tipos
        ArrayAdapter<CharSequence> tipoAdapter = (ArrayAdapter<CharSequence>) tipoSpinner.getAdapter();

        // Retornar la posición del tipo en el adaptador
        return tipoAdapter.getPosition(tipo);
    }
}
