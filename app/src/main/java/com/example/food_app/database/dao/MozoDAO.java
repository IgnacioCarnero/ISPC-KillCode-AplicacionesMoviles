package com.example.food_app.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.food_app.database.entity.mozoEntity;

import java.util.List;

@Dao
public interface MozoDAO {
    @Query("SELECT  * FROM mozo")
    List<mozoEntity> getId_mozo();

    @Query("SELECT  * FROM mozo")
    List<mozoEntity> getNombre();

    @Query("SELECT  * FROM mozo")
    List<mozoEntity> getApellido();

    @Insert

    void insertMozo(mozoEntity mozoEntity);

    @Update
    void updateMozo(mozoEntity mozoEntity);

    @Delete
    void deleteMozo(mozoEntity mozoEntity);
}
