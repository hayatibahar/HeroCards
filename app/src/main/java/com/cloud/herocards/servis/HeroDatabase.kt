package com.cloud.herocards.servis

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.cloud.herocards.model.Hero

@Database(entities = arrayOf(Hero::class), version = 1)
abstract class HeroDatabase : RoomDatabase() {

    abstract fun heroDao() : HeroDAO

    companion object{
        @Volatile private var instance : HeroDatabase? = null
        private val lock = Any()

        operator fun invoke(context : Context) = instance ?: synchronized(lock){
            instance?: createDatabase(context).also {
                instance = it
            }
        }

        private fun createDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            HeroDatabase::class.java,"herodatabase"
        ).build()
    }
}