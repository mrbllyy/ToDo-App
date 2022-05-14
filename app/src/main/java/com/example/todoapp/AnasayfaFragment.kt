package com.example.todoapp

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todoapp.adapter.YapilacaklarAdapter
import com.example.todoapp.databinding.FragmentAnaSayfaBinding
import com.example.todoapp.entity.Yapilacaklar
import com.example.todoapp.viewmodel.AnasayfaFragmentViewModel
import com.example.todoapp.viewmodel.AnasayfaVMF


class AnasayfaFragment: Fragment(), SearchView.OnQueryTextListener, android.widget.SearchView.OnQueryTextListener {
    private lateinit var binding: FragmentAnaSayfaBinding
    private lateinit var viewModel: AnasayfaFragmentViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentAnaSayfaBinding.inflate(layoutInflater, container, false)
        binding.toolbarBaslik = "Yapılacaklar"
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)

        binding.rv.layoutManager = LinearLayoutManager(context)

        viewModel.yapilacaklarListesi.observe(viewLifecycleOwner){
            val adapter = YapilacaklarAdapter(requireContext(), it, viewModel)
            binding.yapilacaklarAdapter = adapter
        }

        binding.searchView.setOnQueryTextListener(this)
        binding.searchView.queryHint = "Yapılacaklarda ara"

        binding.floatingActionButton.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_anaSayfaFragment_to_kayitFragment)
        }
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: AnasayfaFragmentViewModel by viewModels(){
            AnasayfaVMF(requireActivity().application)
        }
        viewModel = tempViewModel
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {

        inflater.inflate(R.menu.menu_toolbar, menu)
        val item = menu.findItem(R.id.action_ara)
        val searchView = item.actionView as SearchView
        searchView.setOnQueryTextListener(this)
        searchView.queryHint = "Yapılacaklarda ara"

        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onQueryTextSubmit(query: String): Boolean {
        Log.e("Yapılacak ara:", query)
        viewModel.ara(query)
        return true
    }

    override fun onQueryTextChange(newText: String): Boolean {
        Log.e("Yapılacak ara:", newText)
        viewModel.ara(newText)
        return true
    }

}