package com.example.gekonapp.domain.convector

import com.example.gekonapp.data.convectorDataBase.ConvectorDB.ConvectorEntity

interface ConvectorRepository {
    suspend fun getByArticle(articleValue: String): ConvectorEntity?
}