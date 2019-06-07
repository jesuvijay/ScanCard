package com.jesu.scancard.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(BusinessCard::class), version = 1)
public abstract class CardDatabase:RoomDatabase() {
    abstract fun BusinessCardDao(): BusinessCardDao

    companion object {
        private var INSTANCE: CardDatabase? = null
        fun getDatabase(context: Context):CardDatabase{
            val tempInstance= INSTANCE
            if (tempInstance!=null){
                return tempInstance
            }
            synchronized(this){
                val instance= Room.databaseBuilder(context.applicationContext,CardDatabase::class.java,"CardDatabase").build()
                INSTANCE=instance
                return instance

            }
        }
    }

}