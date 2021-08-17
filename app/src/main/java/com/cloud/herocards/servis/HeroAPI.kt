package com.cloud.herocards.servis

import com.cloud.herocards.model.Hero
import io.reactivex.Single
import retrofit2.http.GET

interface HeroAPI {

    @GET("karacloud1/heroes-json/main/hero.json")
    fun getHero() : Single<List<Hero>>
}