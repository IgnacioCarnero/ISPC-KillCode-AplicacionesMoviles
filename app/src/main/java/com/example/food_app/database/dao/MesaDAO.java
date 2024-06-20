package com.example.food_app.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;


import com.example.food_app.database.entity.mesaEntity;

import java.util.List;

@Dao
public interface MesaDAO {
    @Query("SELECT  * FROM mesa")
    List<mesaEntity> getId_mesa();

    @Query("SELECT id_mesa FROM mesa WHERE id_mesa = :id_mesa")
    int getId_mesa(int id_mesa);

    @Insert
    void insertMesa(mesaEntity mesaEntity);

    @Update
    void updateMesa(mesaEntity mesaEntity);

    @Delete
    void deleteMesa(mesaEntity mesaEntity);

}


