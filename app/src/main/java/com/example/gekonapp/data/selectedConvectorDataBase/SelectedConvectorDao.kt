package com.example.gekonapp.data.selectedConvectorDataBase

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface SelectedConvectorDao{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addSelectedConvector(selectedConvector: SelectedConvectorEntity)

        @Query("SELECT * FROM selectedConvectorDB")
        suspend fun getConvectorByParams(): MutableList<SelectedConvectorEntity> // Вернется полный список объектов

    @Query("""
        DELETE FROM selectedConvectorDB 
        WHERE number = :number 
        AND article = :article 
        AND name = :name 
        AND power = :power 
        AND price = :price 
        AND count = :count
    """)
    fun deleteConvectorByParams(
        number: Int,
        article: String,
        name: String,
        power: Int,
        price: String,
        count: Int
    )
}