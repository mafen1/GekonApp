package com.example.gekonapp.mainView.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ver1")
data class ConvectorEntity(
    @PrimaryKey(autoGenerate = true)
    val number: Int,
    val article: String,
    val name: String,
    val model: String,
    val power: Int,
    val installationMethod: String,
    val typeLattice: String,
    val typeConvection: String,
    val colorLattice: String,
    val high: Int,
    val weight: Int,
    val lengthn: Int,
    val price: Int,
    val coefficient: String

)
