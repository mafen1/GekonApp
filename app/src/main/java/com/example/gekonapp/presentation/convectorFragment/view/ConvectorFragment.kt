package com.example.gekonapp.presentation.convectorFragment.view

import HeightPickerDialogFragment
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.gekonapp.databinding.FragmentConvectorBinding
import com.example.gekonapp.presentation.convectorFragment.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ConvectorFragment : Fragment() {

    private lateinit var binding: FragmentConvectorBinding
    val mainViewModel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentConvectorBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {

        binding.btnEcoModel.setOnClickListener {
            setupTextColor("Eco", "model")

        }
        binding.btnLevelFModel.setOnClickListener {
            setupTextColor("Level F", "model")

        }
        binding.btnLevelWModel.setOnClickListener {
            setupTextColor("Level W", "model")

        }
        binding.btnVentModel.setOnClickListener {
            setupTextColor("Vent", "model")

        }
        binding.buttonH.setOnClickListener {

            // todo добавить обработчик ошибки если highList=null
            initPickerDialog(mainViewModel.highList.value!!, "high")
        }

        binding.buttonL.setOnClickListener {
            initPickerDialog(mainViewModel.weightList.value!!, "weight")
        }
        binding.buttonA.setOnClickListener {
            initPickerDialog(mainViewModel.lengthList.value!!, "length")
        }

        binding.buttonF.setOnClickListener {
            setupTextColor("F", "typeLatticeButton")
        }
        binding.buttonU.setOnClickListener {
            setupTextColor("U", "typeLatticeButton")
        }
        binding.buttonR.setOnClickListener {
            setupTextColor("R", "typeLatticeButton")
        }
        binding.buttonLatticeL.setOnClickListener {
            setupTextColor("L", "typeLatticeButton")
        }
        binding.buttonNA.setOnClickListener {
            setupTextColor("NA", "ColorLattice")
        }
        binding.buttonBL.setOnClickListener {
            setupTextColor("BL", "ColorLattice")
        }
        binding.buttonLB.setOnClickListener {
            setupTextColor("LB", "ColorLattice")
        }
        binding.buttonDON.setOnClickListener {
            setupTextColor("DON", "ColorLattice")
        }
        binding.btnResult.setOnClickListener {
            mainViewModel.result(binding.root.context)
            mainViewModel.convector.observe(viewLifecycleOwner) {
                setupResult()
            }
        }
        binding.btnPlus.setOnClickListener {
            initCounter("+")
        }
        binding.btnMinus.setOnClickListener {
            initCounter("-")
        }
    }

    private fun setupTextColor(selectedButton: String, groupButton: String) {
        val activeColor = Color.parseColor("#39B54A")
        val inactiveColor = Color.parseColor("#B3B3B3")

        binding.apply {
            if (groupButton == "typeLatticeButton") {
                val buttons = mapOf(
                    "F" to buttonF,
                    "U" to buttonU,
                    "R" to buttonR,
                    "L" to buttonLatticeL
                )

                buttons.forEach { (key, button) ->
                    button.setTextColor(if (key == selectedButton) activeColor else inactiveColor)
                }

                mainViewModel.initTypeLattice(buttons[selectedButton]?.text.toString())
            } else if (groupButton == "ColorLattice") {
                val buttons = mapOf(
                    "NA" to buttonNA,
                    "BL" to buttonBL,
                    "LB" to buttonLB,
                    "DON" to buttonDON
                )

                buttons.forEach { (key, button) ->
                    button.setTextColor(if (key == selectedButton) activeColor else inactiveColor)
                }

                mainViewModel.initSelectedColor(buttons[selectedButton]?.text.toString())
            } else {
                val buttons = mapOf(
                    "Eco" to btnEcoModel,
                    "Vent" to btnVentModel,
                    "Level F" to btnLevelFModel,
                    "Level W" to btnLevelWModel
                )

                buttons.forEach { (key, button) ->
                    button.setTextColor(if (key == selectedButton) activeColor else inactiveColor)
                }

                mainViewModel.addModel(buttons[selectedButton]?.text.toString())
            }
        }
    }

    private fun setupResult() {
        if (mainViewModel.convector.value!!.model!!.isNotEmpty()) {
            binding.tvArticle.visibility = View.VISIBLE
            binding.tvResultArticle.visibility = View.VISIBLE
            binding.tvResultArticle.visibility = View.VISIBLE
            binding.tvCountResult.visibility = View.VISIBLE
            binding.btnPlus.visibility = View.VISIBLE
            binding.btnMinus.visibility = View.VISIBLE
            binding.tvCount.visibility = View.VISIBLE
            binding.tvResultPrice.visibility = View.VISIBLE
            binding.tvPrice.visibility = View.VISIBLE

            binding.tvResultArticle.text = mainViewModel.convector.value!!.article.toString()
            binding.tvPrice.text = mainViewModel.convector.value!!.price.toString()
        } else {
            Toast.makeText(
                requireContext(),
                "Данные конвектора еще не загружены",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun initCounter(operator: String) {
        val convectorPrice = mainViewModel.convector.value?.price
            ?.replace(" ", "") // Убираем пробелы
            ?.replace(",", ".") // Заменяем запятую на точку
            ?.toDoubleOrNull() // Преобразуем в Double

        if (convectorPrice == null) {
            Toast.makeText(context, "Некорректная цена конвектора", Toast.LENGTH_SHORT).show()
            return
        }

        val currentCount = binding.tvCount.text.toString().toIntOrNull() ?: 1

        if (operator == "+") {
            val newCount = currentCount + 1
            binding.tvCount.text = newCount.toString()
            binding.tvPrice.text = String.format("%.2f", newCount * convectorPrice)
        } else if (operator == "-") {
            if (currentCount <= 1) {
                Toast.makeText(
                    context,
                    "Укажите корректное значение для напольного конвектора",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                val newCount = currentCount - 1
                binding.tvCount.text = newCount.toString()
                binding.tvPrice.text = String.format("%.2f", newCount * convectorPrice)
            }
        }
    }

    private fun initPickerDialog(selectedArray: Array<String>, dimension: String) {
        val heightPickerDialog = HeightPickerDialogFragment(selectedArray)
        heightPickerDialog.setOnHeightSelectedListener { selectedLength ->
            mainViewModel.selectedDimensions(selectedLength, dimension)
        }
        heightPickerDialog.show(parentFragmentManager, "heightPicker")
    }

}



