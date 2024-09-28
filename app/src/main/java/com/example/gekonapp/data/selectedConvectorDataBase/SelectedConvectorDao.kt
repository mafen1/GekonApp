package com.example.gekonapp.data.selectedConvectorDataBase

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy

@Dao
interface SelectedConvectorDao{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addSelectedConvector(selectedConvector: SelectedConvectorEntity)
}