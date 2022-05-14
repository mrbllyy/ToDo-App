package com.example.todoapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.AnasayfaFragmentDirections
import com.example.todoapp.R
import com.example.todoapp.databinding.YapilacakCardBinding
import com.example.todoapp.entity.Yapilacaklar
import com.example.todoapp.viewmodel.AnasayfaFragmentViewModel
import com.google.android.material.snackbar.Snackbar
import kotlin.coroutines.coroutineContext

class YapilacaklarAdapter(var mContext: Context,
                          var yapilacaklarListesi: List<Yapilacaklar>,
                          var viewModel: AnasayfaFragmentViewModel): RecyclerView.Adapter<YapilacaklarAdapter.YapilacakCardHolder>() {

    inner class YapilacakCardHolder(binding: YapilacakCardBinding) : RecyclerView.ViewHolder(binding.root){
        var binding: YapilacakCardBinding
        init {
            this.binding = binding
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): YapilacakCardHolder {
        val layoutInflater = LayoutInflater.from(mContext)
        val binding: YapilacakCardBinding = DataBindingUtil.inflate(layoutInflater, R.layout.yapilacak_card, parent, false)
        return YapilacakCardHolder(binding)
    }

    override fun onBindViewHolder(holder: YapilacakCardHolder, position: Int) {
        val yapilacak = yapilacaklarListesi.get(position)
        val t = holder.binding

        t.yapilacak = yapilacak

        t.imageViewSil.setOnClickListener {
            Snackbar.make(it,"${yapilacak.yapilacak_is} silinsin mi?",Snackbar.LENGTH_LONG).setAction("Evet"){
                viewModel.sil(yapilacak)
                Toast.makeText(mContext, "Yapılacak silindi!", Toast.LENGTH_SHORT).show()
                }
                .show()
        }

        t.satirCard.setOnClickListener {
            val gecis = AnasayfaFragmentDirections.actionAnaSayfaFragmentToDetayFragment(yapilacak = yapilacak)
            Navigation.findNavController(it).navigate(gecis)
        }
    }

    override fun getItemCount(): Int {
        return yapilacaklarListesi.size
    }
}