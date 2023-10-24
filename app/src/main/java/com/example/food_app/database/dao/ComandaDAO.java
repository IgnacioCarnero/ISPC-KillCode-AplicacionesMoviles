package com.example.food_app.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.food_app.database.entity.comandaEntity;

import java.util.List;

@Dao
public interface ComandaDAO {
    @Query("SELECT  * FROM comanda")
    List<comandaEntity> getAllCategorias();

    @Query("SELECT * FROM comanda WHERE id_comanda = :comandaID")
    comandaEntity getId_comanda(int comandaID);

    @Query("SELECT * FROM comanda WHERE id_mesa = :mesaID")
    comandaEntity getId_mesa(int mesaID);

    @Query("SELECT * FROM comanda WHERE id_mozo = :mozoID")
    comandaEntity getId_mozo(int mozoID);

    @Insert
    void insertComanda(comandaEntity comandaEntity);

    @Update
    void updateComanda(comandaEntity comandaEntity);

    @Delete
    void deleteComanda(comandaEntity comandaEntity);
}
