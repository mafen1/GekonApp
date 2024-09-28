package com.example.gekonapp.presentation.convectorFragment.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.gekonapp.data.convectorDataBase.ConvectorDB.ConvectorEntity
import com.example.gekonapp.databinding.ConvectorItemBinding
import javax.inject.Inject

class ConvectorAdapter @Inject constructor(
    var convectorList: List<ConvectorEntity?>,
) : RecyclerView.Adapter<ConvectorAdapter.ConvectorViewHolder>() {

    inner class ConvectorViewHolder(private val binding: ConvectorItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(convector: ConvectorEntity, context: Context) {

            binding.tvConvectorName.text = convector.name
            binding.tvResultArticle.text = convector.article
            binding.tvPriceConvector.text = "0.0"
            binding.tvTeplo.text = "Теплоотдача: ${convector.power.toString()}"

            checkCountPrice(binding, convector)

            binding.btnPlus.setOnClickListener {
                binding.tvCountPrice.text = (
                        binding.tvCountPrice.text.toString().toInt() + 1
                        ).toString()

                checkCountPrice(binding, convector)
            }
            binding.btnMinus.setOnClickListener {
                binding.tvCountPrice.text = (
                        if (binding.tvCountPrice.text.toString().toInt() < 1) {
                            Toast.makeText(
                                context,
                                "Укажите корректное значение для напольного конвектора",
                                Toast.LENGTH_SHORT
                            ).show()
                            binding.tvCountPrice.text.toString().toInt()
                        } else {
                            binding.tvCountPrice.text.toString().toInt() - 1
                        }
                        ).toString()

                checkCountPrice(binding, convector)
            }

            binding.button5.setOnClickListener {

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConvectorViewHolder {
        return ConvectorViewHolder(
            ConvectorItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = convectorList.size

    override fun onBindViewHolder(holder: ConvectorViewHolder, position: Int) {
        holder.bind(convectorList[position]!!, holder.itemView.context)
    }

    private fun initViewAdapter(
        binding: ConvectorItemBinding,
        convector: ConvectorEntity,
        context: Context,
        viewHolder: ConvectorViewHolder
    ) {

        binding.tvConvectorName.text = convector.name
        binding.tvResultArticle.text = convector.article
        binding.tvPriceConvector.text = "0.0"
        binding.tvTeplo.text = "Теплоотдача: ${convector.power.toString()}"

        checkCountPrice(binding, convector)

        binding.btnPlus.setOnClickListener {
            binding.tvCountPrice.text = (
                    binding.tvCountPrice.text.toString().toInt() + 1
                    ).toString()

            checkCountPrice(binding, convector)
        }
        binding.btnMinus.setOnClickListener {
            binding.tvCountPrice.text = (
                    if (binding.tvCountPrice.text.toString().toInt() < 1) {
                        Toast.makeText(
                            context,
                            "Укажите корректное значение для напольного конвектора",
                            Toast.LENGTH_SHORT
                        ).show()
                        binding.tvCountPrice.text.toString().toInt()
                    } else {
                        binding.tvCountPrice.text.toString().toInt() - 1
                    }
                    ).toString()

            checkCountPrice(binding, convector)
        }

        binding.button5.setOnClickListener {
//            val position = abso
//            if (position != RecyclerView.NO_POSITION) {
//                listener.onItemClick(dataList[position])
//            }
        }
    }

    private fun checkCountPrice(binding: ConvectorItemBinding, convector: ConvectorEntity) {
        val count =
            binding.tvCountPrice.text.toString().toIntOrNull() ?: 0 // Безопасное преобразование

        if (count == 0) {
            binding.tvPriceConvector.text = "0.0 руб"
        } else {
            // Преобразуйте строку с ценой в Double, предварительно удалив пробелы и запятые
            val price =
                convector.price.toString().replace(" ", "").replace(",", ".").toDoubleOrNull()
                    ?: 0.0

            // Вычислите и установите цену
            val totalPrice = price * count
            binding.tvPriceConvector.text = "$totalPrice руб"
        }
    }


}