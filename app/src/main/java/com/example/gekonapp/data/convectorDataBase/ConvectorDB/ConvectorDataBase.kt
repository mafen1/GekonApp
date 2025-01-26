package com.example.gekonapp.data.convectorDataBase.ConvectorDB

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.gekonapp.data.selectedConvectorDataBase.SelectedConvectorDao
import com.example.gekonapp.data.selectedConvectorDataBase.SelectedConvectorEntity

@Database(
    entities = [ConvectorEntity::class, SelectedConvectorEntity::class],
    version = 4,
    exportSchema = false
)
abstract class ConvectorDataBase : RoomDatabase() {
    abstract fun convectorDao(): ConvectorDao
    abstract fun selectedConvectorDao(): SelectedConvectorDao
}


val MIGRATION_2_3 = object : Migration(2, 3) {
    override fun migrate(database: SupportSQLiteDatabase) {
        // 1. Создать временную таблицу с правильным первичным ключом
        database.execSQL(
            "CREATE TABLE IF NOT EXISTS `selectedConvectorDB_temp` (" +
                    "`article` TEXT NOT NULL, " +
                    "`name` TEXT NOT NULL, " +
                    "`power` INTEGER NOT NULL, " +
                    "`price` TEXT NOT NULL, " +
                    "`count` INTEGER NOT NULL, " +
                    "PRIMARY KEY(`article`))" // Установлен первичный ключ на 'article'
        )

        // 2. Скопировать данные из старой таблицы в временную таблицу
        database.execSQL(
            "INSERT INTO `selectedConvectorDB_temp` (`article`, `name`, `power`, `price`, `count`) " +
                    "SELECT `article`, `name`, `power`, `price`, `count` FROM `selectedConvectorDB`"
        )

        // 3. Удалить старую таблицу
        database.execSQL("DROP TABLE `selectedConvectorDB`")

        // 4. Переименовать временную таблицу в `selectedConvectorDB`
        database.execSQL("ALTER TABLE `selectedConvectorDB_temp` RENAME TO `selectedConvectorDB`")
    }
}
val MIGRATION_3_4 = object : Migration(3, 4) {
    override fun migrate(database: SupportSQLiteDatabase) {
        // 1. Создаем новую таблицу
        database.execSQL("""
            CREATE TABLE IF NOT EXISTS `selectedConvectorDB_new` (
                `number` INTEGER NOT NULL,
                `article` TEXT NOT NULL,
                `name` TEXT NOT NULL,
                `power` INTEGER NOT NULL,
                `price` TEXT NOT NULL,
                `count` INTEGER NOT NULL,
                PRIMARY KEY(`number`)
            )
        """.trimIndent())

        // 2. Перенос данных из старой таблицы в новую
        database.execSQL("""
            INSERT INTO `selectedConvectorDB_new` (article, name, power, price, count)
            SELECT article, name, power, price, count FROM `selectedConvectorDB`
        """.trimIndent())

        // 3. Удаляем старую таблицу
        database.execSQL("DROP TABLE `selectedConvectorDB`")

        // 4. Переименовываем новую таблицу в старое имя
        database.execSQL("ALTER TABLE `selectedConvectorDB_new` RENAME TO `selectedConvectorDB`")
    }
}


val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        // SQL-запрос для создания новой таблицы
        database.execSQL(
            "CREATE TABLE IF NOT EXISTS `selectedConvectorDB` (" +
                    "`name` TEXT NOT NULL, " +
                    "`article` TEXT NOT NULL, " +
                    "`power` INTEGER NOT NULL, " +
                    "`price` TEXT NOT NULL, " +
                    "`count` INTEGER NOT NULL, " +
                    "PRIMARY KEY(`name`))"
        )
    }
}




