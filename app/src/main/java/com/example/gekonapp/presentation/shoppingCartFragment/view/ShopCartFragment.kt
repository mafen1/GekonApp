package com.example.gekonapp.presentation.shoppingCartFragment.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gekonapp.data.selectedConvectorDataBase.SelectedConvectorEntity
import com.example.gekonapp.databinding.FragmentShopCartBinding
import com.example.gekonapp.presentation.shoppingCartFragment.recylerView.ShoppingAdapter
import com.example.gekonapp.presentation.shoppingCartFragment.viewModel.ShopViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ShopCartFragment : Fragment() {

    private lateinit var binding: FragmentShopCartBinding
    private val selectedViewModel: ShopViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentShopCartBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView(){
        selectedViewModel.selectedConvectorList()
        initAdapter(selectedViewModel.selectedConvectorList.value!!)

    }

    private fun initAdapter(selectedConvectorList: MutableList<SelectedConvectorEntity>){

        binding.rcView1.adapter = ShoppingAdapter(selectedConvectorList, selectedViewModel)
        binding.rcView1.layoutManager = LinearLayoutManager(binding.root.context)
    }


}