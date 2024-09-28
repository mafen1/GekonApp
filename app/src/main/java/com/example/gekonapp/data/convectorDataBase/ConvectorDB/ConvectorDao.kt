package com.example.gekonapp.data.convectorDataBase.ConvectorDB

import androidx.room.Dao
import androidx.room.Query

@Dao
interface ConvectorDao {

    @Query("SELECT * FROM gekobdb1 WHERE article = :articleValue")
    suspend fun getByArticle(articleValue: String): ConvectorEntity

}


