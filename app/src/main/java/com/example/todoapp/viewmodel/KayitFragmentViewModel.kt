package com.example.todoapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.todoapp.entity.Yapilacaklar
import com.example.todoapp.repo.YapilacaklarDaoRepo

class KayitFragmentViewModel(application: Application): AndroidViewModel(application) {
    val yapilacaklarDao = YapilacaklarDaoRepo(application)

    fun kayit(yapilacaklar: Yapilacaklar){
        yapilacaklarDao.yapilacakKayit(yapilacaklar)
        yapilacaklarDao.yapilacaklariAl()
    }
}