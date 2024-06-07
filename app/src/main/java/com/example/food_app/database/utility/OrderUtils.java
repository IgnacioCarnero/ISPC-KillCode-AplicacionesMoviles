package com.example.food_app.database.utility;

import com.example.food_app.database.entity.comidaBebida;
import java.util.HashMap;
import java.util.List;

public class OrderUtils {
    // Mapea la comidaBebida al ID con su cantidad
    public static HashMap<Integer, Integer> getCantiadesMap(List<comidaBebida> productos) {
        HashMap<Integer, Integer> cantidades = new HashMap<>();
        for (comidaBebida producto : productos) {
            cantidades.put(producto.getId_comidaBebida(), 0); // Inicializar o establecer la cantidad según sea necesario
        }
        return cantidades;
    }

    public static void actualizarCantidad(comidaBebida comida, int cantidad, HashMap<Integer, Integer> cantidadesPorProducto) {
        cantidadesPorProducto.put(comida.getId_comidaBebida(), cantidad);
    }

    public static int getCantidad(comidaBebida comida, HashMap<Integer, Integer> cantidadesPorProducto) {
        Integer cantidad = cantidadesPorProducto.get(comida.getId_comidaBebida());
        return cantidad != null ? cantidad : 0;
    }
}
