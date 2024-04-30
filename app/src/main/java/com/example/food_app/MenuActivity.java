package com.example.food_app;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.food_app.database.AppDataBase;
import com.example.food_app.database.entity.categoriaEntity;
import com.example.food_app.database.entity.comidaBebida;
import com.example.food_app.database.entity.mesaEntity;

import java.util.List;

public class MenuActivity extends AppCompatActivity {
    ImageButton btn_waiter;
    Button btn_bienvenida;
    Button btn_seleccionar_mesa;
    Button btn_actualizar_menu;
    TextView txtMesaSeleccionada;

    AppDataBase appDataBase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        appDataBase = AppDataBase.getInstance(getApplicationContext());


        btn_bienvenida = findViewById(R.id.btn_bienvenida);
        btn_seleccionar_mesa = findViewById(R.id.btn_seleccionar_mesa);
        btn_actualizar_menu = findViewById(R.id.btn_actualizar_menu);
        txtMesaSeleccionada = findViewById(R.id.txtMesaSeleccionada);
        //insertarMesasDeEjemplo();

        //insertarCategoriasDeEjemplo();
      /*  btn_waiter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuActivity.this, waiterActivity.class);
                startActivity(intent);
            }
        });

       */

        btn_actualizar_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent btn_crudProducts = new Intent(MenuActivity.this, CrudProducts.class);
                startActivity(btn_crudProducts);
            }
        });


        btn_bienvenida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent btn_menu = new Intent(MenuActivity.this, MainActivity.class);
                startActivity(btn_menu);

                // se realiza para despues verificar logs
                List<categoriaEntity> categorias = appDataBase.categoriaDAO().getAllCategorias();
                List<comidaBebida> comidaBebidaEntityList = appDataBase.comidaBebidaDAO().getId_comidaBebida();

                // Imprime las categorías en el registro (log)
                for (categoriaEntity categoria : categorias) {
                    Log.d("Categoría Insertada", "ID: " + categoria.getId_categoria() + ", Nombre: " + categoria.getNombre());
                }
                for (comidaBebida comida : comidaBebidaEntityList) {
                    Log.d("Comida Insertada", "ID: " + comida.getNombre() + ", Nombre: " + comida.getTipo());
                }
            }
        });

        btn_seleccionar_mesa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Crear un cuadro de diálogo de lista con dos opciones
                final CharSequence[] opciones = {"Configurar mesa", "Seleccionar mesa"};
                AlertDialog.Builder builder = new AlertDialog.Builder(MenuActivity.this);
                builder.setTitle("Elige una opción");
                builder.setItems(opciones, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Dependiendo de la opción seleccionada, realiza una acción diferente
                        if (which == 0) {
                            // El usuario seleccionó "Configurar mesa"
                            // Redirige al usuario a la actividad de configuración de mesa
                            Intent intentConfigurarMesa = new Intent(MenuActivity.this, ConfigurarMesaActivity.class);
                            startActivity(intentConfigurarMesa);
                        } else if (which == 1) {
                            // El usuario seleccionó "Seleccionar mesa"
                            // Llama al método original para seleccionar mesa
                            seleccionarMesa();
                        }
                    }
                });

                // Mostrar el cuadro de diálogo
                builder.show();
            }

            // Método para seleccionar mesa (originalmente parte del método onClick)
            private void seleccionarMesa() {
                // Crea un cuadro de diálogo para ingresar el número de mesa
                AlertDialog.Builder builder = new AlertDialog.Builder(MenuActivity.this);
                builder.setTitle("Ingrese el número de mesa");

                // Agrega un campo de entrada de texto al cuadro de diálogo
                final EditText input = new EditText(MenuActivity.this);
                builder.setView(input);

                // Configura los botones del cuadro de diálogo (Aceptar y Cancelar)
                builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Aquí obtienes el valor ingresado por el usuario y ejecutas la consulta a la base de datos
                        String numeroMesaStr = input.getText().toString();
                        if (!numeroMesaStr.isEmpty()) {
                            int numeroMesa = Integer.parseInt(numeroMesaStr);
                            int idMesa = appDataBase.mesaDAO().getId_mesa(numeroMesa);

                            if (idMesa != 0) {
                                // El número de mesa existe en la base de datos
                                // Actualiza el TextView con el mensaje
                                String mensaje = "Mesa seleccionada: " + numeroMesa;
                                txtMesaSeleccionada.setText(mensaje);
                                txtMesaSeleccionada.setVisibility(View.VISIBLE); // Cambia la visibilidad a "visible"
                            } else {
                                // El número de mesa no existe en la base de datos
                                Toast.makeText(MenuActivity.this, "Mesa no encontrada en la base de datos.", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            // El campo está vacío
                            Toast.makeText(MenuActivity.this, "Por favor, ingrese un número de mesa válido.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // El usuario canceló el cuadro de diálogo
                        dialog.cancel();
                    }
                });

                // Muestra el cuadro de diálogo
                builder.show();
            }
        });}


    // Función para consultar la mesa por su número
    private void consultarMesa(int numeroMesa) {
        // Aquí debes implementar la lógica para consultar la mesa en la base de datos
        // Utiliza appDataBase.mesaDAO().getId_mesa(numeroMesa) para obtener la mesa

        Integer mesaSeleccionada = appDataBase.mesaDAO().getId_mesa(numeroMesa);

        if (mesaSeleccionada != null) {
            // La mesa se encontró en la base de datos, lo que significa que existe
            // Puedes realizar acciones con la mesa seleccionada, como mostrar información o iniciar una actividad
            Log.d("Consulta de Mesa", "Mesa seleccionada: ID " + mesaSeleccionada);

            // Por ejemplo, puedes iniciar una nueva actividad y pasar la mesa seleccionada como un extra
            Intent intent = new Intent(MenuActivity.this, MenuScroll.class);
            intent.putExtra("mesaSeleccionada", mesaSeleccionada);
            startActivity(intent);
        } else {
            // La mesa no se encontró en la base de datos
            // Puedes mostrar un mensaje de error o realizar otras acciones según tus necesidades
            Log.e("Consulta de Mesa", "La mesa no se encontró en la base de datos.");
        }
    }
    private void insertarMesasDeEjemplo() {
        // Insertar mesas de ejemplo
        for (int i = 2; i <= 5; i++) {
            mesaEntity mesa = new mesaEntity();
            mesa.setId_mesa(i); // Cambia el ID de acuerdo a tus necesidades
            appDataBase.mesaDAO().insertMesa(mesa);
        }
    }

    private void insertarCategoriasDeEjemplo(){
        categoriaEntity uno = new categoriaEntity("Entrada");
        categoriaEntity dos = new categoriaEntity("Plato principal");
        categoriaEntity tres = new categoriaEntity("Postre");
        categoriaEntity cuatro = new categoriaEntity("Bebida");
        appDataBase.categoriaDAO().insertCategoria(uno);
        appDataBase.categoriaDAO().insertCategoria(dos);
        appDataBase.categoriaDAO().insertCategoria(tres);
        appDataBase.categoriaDAO().insertCategoria(cuatro);
    }
    private void eliminarRegistroComidaBebida() {
        // Aquí ejecuta la lógica para eliminar el registro que desees
        // Por ejemplo, elimina el registro con un ID específico, como el 12
        AppDataBase appDataBase = AppDataBase.getInstance(getApplicationContext());
        List<comidaBebida> comidaBebidaEntityList = appDataBase.comidaBebidaDAO().getId_comidaBebida();
        if (comidaBebidaEntityList.size() > 1) {
            appDataBase.comidaBebidaDAO().deleteComidaBebida(comidaBebidaEntityList.get(9));
        }
    }
}