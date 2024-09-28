package com.example.gekonapp.data.convectorDataBase.ConvectorDB

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "gekobdb1")
data class ConvectorEntity(
    val typeConvection: String?,
    val weight: Int?,
    val typeLattice: String?,
    val article: String?,
    @PrimaryKey
    val number: Int?,
    val colorLattice: String?,
    val lengthn: Int?,
    val high: Int?,
    val price: String?,
    val name: String?,
    val installationMethod: String?,
    val coefficient: String?,
    val model: String?,
    val power: Int?,
)
