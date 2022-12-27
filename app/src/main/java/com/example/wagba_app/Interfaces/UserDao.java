package com.example.wagba_app.Interfaces;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.wagba_app.Models.User;

@Dao
public interface UserDao {
    @Insert(onConflict =  OnConflictStrategy.REPLACE)
    void insert (User user);


    @Query("SELECT * from user_table")
    User getUsers();

    @Query("DELETE FROM user_table")
    void deleteAll();
}
