package com.example.gekonapp.mainView.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ConvectorEntity::class], version = 1)
abstract class ConvectorDataBase: RoomDatabase(){
    abstract fun convectorDao(): ConvectorDao
}

object DatabaseBuilder {
    private var instance: ConvectorDataBase? = null

    fun getInstance(context: Context): ConvectorDataBase{
        if (instance == null){
            synchronized(ConvectorDataBase::class){
                instance = Room.databaseBuilder(
                        context.applicationContext,
                    ConvectorDataBase::class.java,
                    "con"
                )
                    .createFromAsset(databaseFilePath = "gekkk.sql")
                    .build()
            }
        }
        return instance!!
    }
}