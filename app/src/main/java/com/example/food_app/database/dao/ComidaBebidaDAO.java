package com.example.food_app.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.food_app.database.entity.comidaBebida;

import java.util.List;

@Dao
public interface ComidaBebidaDAO {

    @Query("SELECT  * FROM comidaBebida")
    List<comidaBebida> getId_comidaBebida();

    @Query("SELECT * FROM comidaBebida WHERE id_comidaBebida = :comidaBebidaID")
    comidaBebida getId_comidaBebida(int comidaBebidaID);

    @Query("SELECT nombre FROM comidaBebida")
    List<String> getNombres();

    @Query("SELECT * FROM comidaBebida WHERE  tipo= :tipoID")
    List<comidaBebida> getTipo(int tipoID);

    @Query("SELECT * FROM comidaBebida WHERE  precio= :precioID")
    comidaBebida getPresio(int precioID);

    @Query("SELECT * FROM comidaBebida WHERE  descripcion= :descripID")
    comidaBebida getDescripcion(int descripID);

    @Query("SELECT * FROM comidaBebida WHERE  id_categoria = :categotiaID")
    List<comidaBebida> getId_categoria(int categotiaID);


    @Insert
    void insertComidaBebida(comidaBebida comidaBebida);

    @Update
    void updateComidaBebida(comidaBebida comidaBebida);

    @Delete
    void deleteComidaBebida(comidaBebida comidaBebida);
}