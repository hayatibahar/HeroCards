package com.cloud.herocards.servis

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.cloud.herocards.model.Hero

@Dao
interface HeroDAO {
    @Insert
    suspend fun insertAll(vararg hero: Hero) : List<Long>

    @Query("SELECT * FROM hero")
    suspend fun getAllHero() : List<Hero>

    @Query("SELECT * FROM hero where uuid = :heroId")
    suspend fun getHero(heroId : Int) : Hero

    @Query("DELETE FROM hero")
    suspend fun deleteAllHero()
}