package com.example.gekonapp.data.selectedConvectorDataBase

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "selectedConvectorDB")
data class SelectedConvectorEntity (
    @PrimaryKey
    val number: Int,
    val article: String,
    val name: String,
    val power: Int,
    val price: String,
    val count: Int
)