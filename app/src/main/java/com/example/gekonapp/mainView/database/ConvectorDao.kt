package com.example.gekonapp.mainView.database

import androidx.room.Dao
import androidx.room.Query

@Dao
interface ConvectorDao {
    @Query("SELECT * FROM ver1")
    fun getAll(): List<ConvectorEntity>
}