package com.example.todoapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.example.todoapp.entity.Yapilacaklar
import com.example.todoapp.repo.YapilacaklarDaoRepo

class DetayFragmentViewModel(application: Application): AndroidViewModel(application) {
    val yapilacaklarDao = YapilacaklarDaoRepo(application)

    fun guncelle(yapilacaklar: Yapilacaklar, string: String){
        yapilacaklarDao.yapilacakGuncelle(yapilacaklar, string)
    }

}