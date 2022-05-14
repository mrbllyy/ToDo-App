package com.example.todoapp.room

import androidx.room.*
import com.example.todoapp.entity.Yapilacaklar

@Dao
interface YapilacaklarDao {
    @Query("SELECT * FROM yapilacaklar")
    suspend fun tumYapilacaklar(): List<Yapilacaklar>

    @Insert
    suspend fun yapilacakEkle(yapilacaklar: Yapilacaklar)

    @Update
    suspend fun yapilacakGuncelle(yapilacaklar: Yapilacaklar)

    @Delete
    suspend fun yapilacakSil(yapilacaklar: Yapilacaklar)

    @Query("SELECT * FROM yapilacaklar WHERE yapilacak_is LIKE '%' || :aramaKelimesi || '%'")
    suspend fun yapilacakArama(aramaKelimesi: String): List<Yapilacaklar>

}