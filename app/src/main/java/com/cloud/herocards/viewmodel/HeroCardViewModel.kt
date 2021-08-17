package com.cloud.herocards.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.cloud.herocards.model.Hero
import com.cloud.herocards.servis.HeroDatabase
import kotlinx.coroutines.launch

class HeroCardViewModel(application: Application) : BaseViewModel(application) {

    val heroLiveData = MutableLiveData<Hero>()

    fun getRoomDataById(uuid : Int){
        launch {
            val dao = HeroDatabase(getApplication()).heroDao()
            val hero = dao.getHero(uuid)
            heroLiveData.value = hero
        }
    }
}