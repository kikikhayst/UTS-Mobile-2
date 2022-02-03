package com.rizqirama.utsmobiledua.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.rizqirama.utsmobiledua.model.Data

@Database(entities = [Data::class], version = 1)
abstract class MyDatabase : RoomDatabase() {
    abstract fun dataAccessObject() : DataAccessObject

    companion object{
        private var INSTANCE: MyDatabase? = null

        fun getInstance(context: Context) : MyDatabase? {
            if (INSTANCE == null) {
                synchronized(MyDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            MyDatabase::class.java, "Barang_db"
                    ).allowMainThreadQueries().build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}