package com.example.gekonapp.presentation.shoppingCartFragment.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gekonapp.data.selectedConvectorDataBase.SelectedConvectorEntity
import com.example.gekonapp.data.selectedConvectorDataBase.repositoryImpl.SelectedConvectorRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ShopViewModel @Inject constructor(
    private val selectedRepositoryImpl: SelectedConvectorRepositoryImpl
) : ViewModel() {

    val selectedConvectorList: MutableLiveData<MutableList<SelectedConvectorEntity>> = MutableLiveData()

    fun deleteSelectedConvector(
        number: Int,
        article: String,
        name: String,
        power: Int,
        price: String,
        count: Int
    ) {
        selectedRepositoryImpl.deleteConvectorByParams(
            number, article, name, power, price, count
        )
    }

    fun selectedConvectorList() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                selectedConvectorList.value = selectedRepositoryImpl.getConvector()
            }
        }
    }
}