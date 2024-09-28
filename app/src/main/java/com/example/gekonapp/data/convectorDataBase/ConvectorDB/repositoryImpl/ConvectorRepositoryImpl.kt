package com.example.gekonapp.data.convectorDataBase.ConvectorDB.repositoryImpl

import com.example.gekonapp.data.convectorDataBase.ConvectorDB.ConvectorDao
import com.example.gekonapp.data.convectorDataBase.ConvectorDB.ConvectorEntity
import com.example.gekonapp.domain.convector.ConvectorRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ConvectorRepositoryImpl @Inject constructor(private val convectorDao: ConvectorDao):
    ConvectorRepository {
    override suspend fun getByArticle(articleValue: String): ConvectorEntity = convectorDao.getByArticle(articleValue)
}

