package com.example.food_app.menu;

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

import com.example.food_app.ConfigurarCategoriaActivity;
import com.example.food_app.ConfigurarMesaActivity;
import com.example.food_app.CrudProducts;
import com.example.food_app.R;
import com.example.food_app.auth.LoginActivity;
import com.example.food_app.database.AppDataBase;
import com.example.food_app.database.entity.categoriaEntity;
import com.example.food_app.database.entity.comidaBebida;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

public class MenuActivity extends AppCompatActivity {
    ImageButton btn_waiter;
    Button btn_bienvenida;
    Button btn_seleccionar_mesa;
    Button btn_actualizar_menu;
    TextView txtMesaSeleccionada;
    Button btn_config_categoria;
    Button btnLogout;
    AppDataBase appDataBase;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        appDataBase = AppDataBase.getInstance(getApplicationContext());

        mAuth = FirebaseAuth.getInstance();
        btn_bienvenida = findViewById(R.id.btn_bienvenida);
        btn_seleccionar_mesa = findViewById(R.id.btn_seleccionar_mesa);
        btn_actualizar_menu = findViewById(R.id.btn_actualizar_menu);
        btn_config_categoria = findViewById(R.id.btn_config_categoria);
        btnLogout = findViewById(R.id.btnLogout);
        txtMesaSeleccionada = findViewById(R.id.txtMesaSeleccionada);


        btn_actualizar_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent btn_crudProducts = new Intent(MenuActivity.this, CrudProducts.class);
                startActivity(btn_crudProducts);
            }
        });

        btn_config_categoria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentConfigurarCategoria = new Intent(MenuActivity.this, ConfigurarCategoriaActivity.class);
                startActivity(intentConfigurarCategoria);
            }
        });
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mAuth != null) {
                    mAuth.signOut();
                    Intent intent = new Intent(MenuActivity.this, LoginActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(MenuActivity.this, "Error al cerrar sesión. Intente nuevamente.", Toast.LENGTH_SHORT).show();
                }
            }
        });


        btn_bienvenida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent btn_menu = new Intent(MenuActivity.this, MenuScroll.class);
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
}