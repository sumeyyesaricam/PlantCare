package example.smyy.plantcare.data

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import android.content.Context
import example.smyy.plantcare.data.dao.PlantDao
import example.smyy.plantcare.data.model.db.Plant

@Database(entities = [Plant::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun plantDao(): PlantDao

    companion object {
        private const val DB_NAME = "plant.db"

        fun createPersistentDatabase(context: Context): AppDatabase
                = Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, DB_NAME).build()
    }

}