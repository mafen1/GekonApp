package com.example.gekonapp.presentation.convectorFragment.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.RadioButton
import android.widget.Spinner
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.example.gekonapp.R
import com.example.gekonapp.databinding.FragmentConvectorBinding
import com.example.gekonapp.presentation.convectorFragment.adapter.ConvectorAdapter
import com.example.gekonapp.presentation.convectorFragment.viewmodel.MainViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ConvectorFragment : Fragment() {

    private lateinit var binding: FragmentConvectorBinding
    val mainViewModel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentConvectorBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        initRadioGroup()
        initRadioGroup2()
        initFAB()
        binding.btnEcoModel.setOnClickListener {
            mainViewModel.addModel("Eco")
            initSpinner()

        }
        binding.btnLevelFModel.setOnClickListener {
            mainViewModel.addModel("Level F")
            initSpinner()
        }
        binding.btnLevelWModel.setOnClickListener {
            mainViewModel.addModel("Level W")
            initSpinner()
        }
        binding.btnVentModel.setOnClickListener {
            mainViewModel.addModel("Vent")
            initSpinner()
        }
        binding.button2.setOnClickListener {
            initAdapter()
        }
        binding.floatingActionButton.setOnClickListener {

            //TODO: добавить открытие ShoppingCartFragment

            Navigation.findNavController(requireView())
                .navigate(R.id.action_convectorFragment_to_shopCartFragment)


        }


    }


    private fun initSpinner() {
        setupSpinner(binding.spinner, mainViewModel.highList.value!!, "high")
        setupSpinner(binding.spinner2, mainViewModel.lengthList.value!!, "length")
        setupSpinner(binding.spinner3, mainViewModel.weightList.value!!, "weight")
    }

    private fun setupSpinner(spinner: Spinner, dataList: List<String>, dimensionType: String) {
        val spinnerAdapter =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, dataList)
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = spinnerAdapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                mainViewModel.selectedDimensions(parent?.selectedItem.toString(), dimensionType)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                mainViewModel.selectedDimensions(dataList[0], dimensionType)
            }
        }
    }

    private fun initRadioGroup() {
        binding.radioGroup.setOnCheckedChangeListener { group, checkedId ->
            val selectedRadioButton = group.findViewById<RadioButton>(checkedId)
            mainViewModel.initTypeLattice(selectedRadioButton.tag.toString())
        }
    }

    private fun initRadioGroup2() {
        binding.radioGroup2.setOnCheckedChangeListener { group, checkedId ->
            val selectedRadioButton = group.findViewById<RadioButton>(checkedId)
            mainViewModel.initSelectedColor(selectedRadioButton.tag.toString())
        }
    }


    private fun initAdapter() {
        mainViewModel.result(binding.root.context)
        mainViewModel.convector.observe(viewLifecycleOwner) { convector ->
            val convectorAdapter = ConvectorAdapter(listOf(convector))
            binding.rcView.adapter = convectorAdapter
            binding.rcView.layoutManager = GridLayoutManager(requireContext(), 1)
        }
        mainViewModel.number.value = mainViewModel.number.value?.plus(1)
    }

    private fun initFAB() {
        val params = binding.floatingActionButton.layoutParams as CoordinatorLayout.LayoutParams
        params.anchorId = binding.rcView.id
        params.behavior = FloatingActionButton.Behavior()

        binding.floatingActionButton.layoutParams = params
    }

//    fun init

}