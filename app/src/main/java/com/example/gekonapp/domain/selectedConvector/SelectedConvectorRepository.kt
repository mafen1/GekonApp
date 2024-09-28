package com.example.gekonapp.domain.selectedConvector

import com.example.gekonapp.data.selectedConvectorDataBase.SelectedConvectorEntity

interface SelectedConvectorRepository {

    suspend fun addSelectedConvector(selectedConvector: SelectedConvectorEntity)

}