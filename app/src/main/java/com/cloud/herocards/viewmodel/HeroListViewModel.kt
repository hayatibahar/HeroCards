package com.cloud.herocards.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.cloud.herocards.model.Hero
import com.cloud.herocards.servis.HeroAPIServis
import com.cloud.herocards.servis.HeroDatabase
import com.cloud.herocards.util.CustomSharedPreferences
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch

class HeroListViewModel(application: Application) : BaseViewModel(application) {
    val heroes = MutableLiveData<List<Hero>>()
    val errorMessage = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()
    private val heroAPIServis = HeroAPIServis()
    private val disposable = CompositeDisposable()
    private val customSharedPreferences = CustomSharedPreferences(getApplication())
    private var updateTime = 10 * 60 * 1000 * 1000 * 1000L

    fun refreshData(){
        val savedTime = customSharedPreferences.getTime()
        if (savedTime!=null && savedTime != 0L && System.nanoTime() - savedTime < updateTime){
            getDataFromSQLite()
        }
        else{
            getDataFromWeb()

        }
    }
    fun refreshFromWeb(){
        getDataFromWeb()
    }

    private fun getDataFromSQLite(){
        loading.value = true
        launch {
            val heroList = HeroDatabase(getApplication()).heroDao().getAllHero()
            showHeroes(heroList)
        }
    }

    private fun showHeroes(heroList : List<Hero>){
        heroes.value = heroList
        errorMessage.value = false
        loading.value = false
    }

    private fun getDataFromWeb(){
        loading.value = true

        disposable.add(
            heroAPIServis.getData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object  : DisposableSingleObserver<List<Hero>>(){
                    override fun onSuccess(t: List<Hero>) {
                        saveSQLite(t)
                    }

                    override fun onError(e: Throwable) {
                        errorMessage.value = true
                        loading.value = false
                        e.printStackTrace()
                    }

                })
        )
    }

    private fun saveSQLite(heroList : List<Hero>){
        launch {
            val dao = HeroDatabase(getApplication()).heroDao()
            dao.deleteAllHero()
            val uuidList = dao.insertAll(*heroList.toTypedArray())
            var i = 0
            while (i<heroList.size){
                heroList[i].uuid = uuidList[i].toInt()
                i += 1
            }
            showHeroes(heroList)
        }
        customSharedPreferences.saveTime(System.nanoTime())
    }

}