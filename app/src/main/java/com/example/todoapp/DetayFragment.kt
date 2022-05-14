package com.example.todoapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.todoapp.databinding.FragmentDetayBinding
import com.example.todoapp.entity.Yapilacaklar
import com.example.todoapp.viewmodel.DetayFragmentViewModel
import com.example.todoapp.viewmodel.DetayVMF
import com.example.todoapp.viewmodel.KayitFragmentViewModel
import com.example.todoapp.viewmodel.KayitVMF

class DetayFragment : Fragment() {
    private lateinit var binding: FragmentDetayBinding
    private lateinit var viewModel: DetayFragmentViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentDetayBinding.inflate(inflater, container, false)
        binding.toolbarDetayBaslik = "Yapılacak İş Detay"

        val bundle: DetayFragmentArgs by navArgs()
        val gelenYapilacak = bundle.yapilacak
        binding.yapilacak = gelenYapilacak

        binding.buttonGuncelle.setOnClickListener {
            viewModel.guncelle(gelenYapilacak, binding.textInputDetay.text.toString())
            Navigation.findNavController(it).navigate(R.id.action_detayFragment_to_anaSayfaFragment)
            Toast.makeText(context, "Yapılacak güncellendi!", Toast.LENGTH_SHORT).show()
        }

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: DetayFragmentViewModel by viewModels(){
            DetayVMF(requireActivity().application)
        }
        viewModel = tempViewModel
    }


}