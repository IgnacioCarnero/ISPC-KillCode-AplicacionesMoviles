package com.example.food_app.database.dao;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.food_app.database.entity.categoriaEntity;
import java.util.List;

@Dao
public interface CategoriaDAO {
    @Query("SELECT  * FROM categoria")
    List<categoriaEntity> getAllCategorias(); //recupera todas las categorias

    @Query("SELECT * FROM categoria WHERE id_categoria = :categoriaID")
    categoriaEntity getCategoriaById(int categoriaID); //se utiliza para recuperar una categoria con un ID especifico

    @Insert
    void insertCategoria(categoriaEntity categoriaEntity); //Se utiliza para insertar una nueva categoria en la base de datos

    @Update
    void updateCategoria(categoriaEntity categoriaEntity); //Se utiliza para actualizar una nueva categoria en la base de datos

    @Delete
    void deleteCategoria(categoriaEntity categoriaEntity); //Se utiliza para eliminar una categoria en la base de datos
}
