package com.example.gekonapp.di

import android.content.Context
import androidx.room.Room
import com.example.gekonapp.data.convectorDataBase.ConvectorDB.ConvectorDataBase
import com.example.gekonapp.data.convectorDataBase.ConvectorDB.MIGRATION_1_2
import com.example.gekonapp.data.convectorDataBase.ConvectorDB.MIGRATION_2_3
import com.example.gekonapp.data.convectorDataBase.ConvectorDB.MIGRATION_3_4
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    fun provideDatabase(@ApplicationContext context: Context): ConvectorDataBase {
        return Room.databaseBuilder(
            context.applicationContext,
            ConvectorDataBase::class.java,
            "DBGekon8"
        )
            .addMigrations(MIGRATION_1_2, MIGRATION_2_3, MIGRATION_3_4)
            .createFromAsset("databasegekon1.db")
            .build()
    }
        @Provides
        @Singleton
        fun provideConvectorDao(database: ConvectorDataBase) = database.convectorDao()


    @Provides
    @Singleton
    fun provideSelectedConvectorDao(database: ConvectorDataBase) = database.selectedConvectorDao()

//    @Provides
//    fun provideSelectedConvectorDatabase(@ApplicationContext context: Context): SelectedConvector {
//        return Room.databaseBuilder(
//            context.applicationContext,
//            SelectedConvector::class.java,
//            "selectedConvectorDB"
//        )
//            .build()
//    }
//
//    @Provides
//    @Singleton
//    fun provideSelectedConvectorDao(database: SelectedConvector) = database.convectorDao()



}