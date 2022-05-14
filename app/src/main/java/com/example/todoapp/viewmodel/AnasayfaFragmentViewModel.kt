package com.example.todoapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.todoapp.entity.Yapilacaklar
import com.example.todoapp.repo.YapilacaklarDaoRepo

class AnasayfaFragmentViewModel(application: Application): AndroidViewModel(application) {

    val yapilacaklarDao = YapilacaklarDaoRepo(application)
    var yapilacaklarListesi = MutableLiveData<List<Yapilacaklar>>()

    init {
        yapilacaklariYukle()
        yapilacaklarListesi = yapilacaklarDao.yapilacaklariAl()
    }

    fun sil(yapilacaklar: Yapilacaklar){
        yapilacaklarDao.yapilacakSil(yapilacaklar)
    }

    fun yapilacaklariYukle() {
        yapilacaklarDao.yapilacaklariAl()
    }

    fun ara(query: String) {
        yapilacaklarDao.yapilacakAra(query)
    }

}