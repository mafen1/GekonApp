package com.example.gekonapp.domain.selectedConvector

import com.example.gekonapp.data.selectedConvectorDataBase.SelectedConvectorEntity

interface SelectedConvectorRepository {

    suspend fun addSelectedConvector(selectedConvector: SelectedConvectorEntity)


    fun deleteConvectorByParams(
        number: Int,
        article: String,
        name: String,
        power: Int,
        price: String,
        count: Int
    )

    suspend fun getConvector(): MutableList<SelectedConvectorEntity>
}