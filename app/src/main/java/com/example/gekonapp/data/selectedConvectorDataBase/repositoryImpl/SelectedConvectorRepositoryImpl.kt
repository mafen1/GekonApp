package com.example.gekonapp.data.selectedConvectorDataBase.repositoryImpl

import com.example.gekonapp.data.selectedConvectorDataBase.SelectedConvectorDao
import com.example.gekonapp.data.selectedConvectorDataBase.SelectedConvectorEntity
import com.example.gekonapp.domain.selectedConvector.SelectedConvectorRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SelectedConvectorRepositoryImpl @Inject constructor(
    private val selectedConvectorDao: SelectedConvectorDao
) : SelectedConvectorRepository {
    override suspend fun addSelectedConvector(selectedConvector: SelectedConvectorEntity) {
        selectedConvectorDao.addSelectedConvector(selectedConvector)
    }



    override fun deleteConvectorByParams(
        number: Int,
        article: String,
        name: String,
        power: Int,
        price: String,
        count: Int
    ) {
        selectedConvectorDao.deleteConvectorByParams(
            number, article, name, power, price, count
        )
    }

    override suspend fun getConvector(): MutableList<SelectedConvectorEntity> = selectedConvectorDao.getConvectorByParams()
}