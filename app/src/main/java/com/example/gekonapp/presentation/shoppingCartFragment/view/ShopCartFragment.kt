package com.example.gekonapp.presentation.shoppingCartFragment.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.gekonapp.databinding.FragmentShopCartBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ShopCartFragment : Fragment() {

    private lateinit var binding: FragmentShopCartBinding

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

    }

    private fun initAdapter(shopList: MutableList<String>){
//        binding.rcView.adapter = ShoppingAdapter(shopList)
    }


}