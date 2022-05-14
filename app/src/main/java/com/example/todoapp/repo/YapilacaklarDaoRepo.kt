package com.example.todoapp.repo

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.example.todoapp.entity.Yapilacaklar
import com.example.todoapp.room.Veritabani
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class YapilacaklarDaoRepo(application: Application) {
    var yapilacaklarListesi: MutableLiveData<List<Yapilacaklar>>
    var veritabani: Veritabani

    init {
        yapilacaklarListesi = MutableLiveData()
        veritabani = Veritabani.veritabaniErisim(application)!!
    }

    fun yapilacaklariAl(): MutableLiveData<List<Yapilacaklar>>{
        val job = CoroutineScope(Dispatchers.Main).launch {
            yapilacaklarListesi.value = veritabani.yapilacaklarDao().tumYapilacaklar()
        }
        return yapilacaklarListesi
    }

    fun yapilacakKayit(yapilacaklar: Yapilacaklar) {
        val job = CoroutineScope(Dispatchers.Main).launch {
            veritabani.yapilacaklarDao().yapilacakEkle(yapilacaklar)
        }
    }

    fun yapilacakSil(yapilacaklar: Yapilacaklar) {
        val job = CoroutineScope(Dispatchers.Main).launch {
            veritabani.yapilacaklarDao().yapilacakSil(yapilacaklar)
            yapilacaklariAl()
        }
    }

    fun yapilacakGuncelle(yapilacaklar: Yapilacaklar, string: String) {
        yapilacaklar.yapilacak_is = string
        val job = CoroutineScope(Dispatchers.Main).launch {
            veritabani.yapilacaklarDao().yapilacakGuncelle(yapilacaklar)
        }
    }

    fun yapilacakAra(string: String) {
        val job = CoroutineScope(Dispatchers.Main).launch {
            yapilacaklarListesi.value = veritabani.yapilacaklarDao().yapilacakArama(string)
        }
    }
}