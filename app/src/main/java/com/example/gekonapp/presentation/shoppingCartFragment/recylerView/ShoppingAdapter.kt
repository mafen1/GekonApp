package com.example.gekonapp.presentation.shoppingCartFragment.recylerView

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.gekonapp.data.selectedConvectorDataBase.SelectedConvectorEntity
import com.example.gekonapp.databinding.ShopItemBinding
import com.example.gekonapp.presentation.shoppingCartFragment.viewModel.ShopViewModel

class ShoppingAdapter(
    var selectedConvectorList: MutableList<SelectedConvectorEntity>,
    val selectedConvectorViewModel: ShopViewModel
) : RecyclerView.Adapter<ShoppingAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ShopItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
            fun bind(selectedConvector: SelectedConvectorEntity){

                // инициализируем textView
                binding.tvName.text = selectedConvector.name
                binding.tvCount.text = selectedConvector.count.toString()
                binding.tvPrice.text = selectedConvector.price
                binding.tvCount.text = selectedConvector.count.toString()

                binding.ivDelete.setOnClickListener{
                    // удаляем выбранный конвертор
                    selectedConvectorViewModel.deleteSelectedConvector(
                        number = selectedConvector.number,
                        article = selectedConvector.article,
                        name = selectedConvector.name,
                        power = selectedConvector.power,
                        price = selectedConvector.price,
                        count = selectedConvector.count
                    )
                }

                binding.btnPlus.setOnClickListener {
                    binding.tvCount.text = (
                            binding.tvCount.text.toString().toInt() + 1
                            ).toString()

                    checkCountPrice(binding, selectedConvector)
                }
                binding.btnMinus.setOnClickListener {
                    binding.tvCount.text = (
                            if (binding.tvCount.text.toString().toInt() < 1) {
                                Toast.makeText(
                                    binding.root.context,
                                    "Укажите корректное значение для напольного конвектора",
                                    Toast.LENGTH_SHORT
                                ).show()
                                binding.tvCount.text.toString().toInt()
                            } else {
                                binding.tvCount.text.toString().toInt() - 1
                            }
                            ).toString()

                    checkCountPrice(binding, selectedConvector)
                }

            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ShopItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ))
    }

    override fun getItemCount(): Int = selectedConvectorList.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(selectedConvectorList[position])
    }

    private fun checkCountPrice(binding: ShopItemBinding, convector: SelectedConvectorEntity) {
        val count =
            binding.tvCount.text.toString().toIntOrNull() ?: 0 // Безопасное преобразование

        if (count == 0) {
            binding.tvPrice.text = "0.0 руб"
        } else {
            // Преобразуйте строку с ценой в Double, предварительно удалив пробелы и запятые
            val price =
                convector.price.replace(" ", "").replace(",", ".").toDoubleOrNull()
                    ?: 0.0

            // Вычислите и установите цену
            val totalPrice = price * count
            binding.tvPrice.text = "$totalPrice руб"
        }
    }
}