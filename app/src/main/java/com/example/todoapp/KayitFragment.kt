package com.example.todoapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.todoapp.databinding.FragmentKayitBinding
import com.example.todoapp.entity.Yapilacaklar
import com.example.todoapp.viewmodel.KayitFragmentViewModel
import com.example.todoapp.viewmodel.KayitVMF


class KayitFragment : Fragment() {
    private lateinit var binding: FragmentKayitBinding
    private lateinit var viewModel: KayitFragmentViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_kayit, container, false)
        binding.toolbarKayitBaslik = "Yapılacak İş Kayıt"
        binding.button.setOnClickListener {
            viewModel.kayit(Yapilacaklar(0, binding.textInputKayit.text.toString()))
            Navigation.findNavController(it).navigate(R.id.action_kayitFragment_to_anaSayfaFragment)
            Toast.makeText(context, "Yapılacak kaydedildi!", Toast.LENGTH_SHORT).show()
        }
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: KayitFragmentViewModel by viewModels(){
            KayitVMF(requireActivity().application)
        }
        viewModel = tempViewModel
    }


}