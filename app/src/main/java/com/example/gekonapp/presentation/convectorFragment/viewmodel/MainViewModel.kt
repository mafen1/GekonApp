package com.example.gekonapp.presentation.convectorFragment.viewmodel

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gekonapp.data.convectorDataBase.ConvectorDB.ConvectorEntity
import com.example.gekonapp.data.convectorDataBase.ConvectorDB.repositoryImpl.ConvectorRepositoryImpl
import com.example.gekonapp.data.selectedConvectorDataBase.SelectedConvectorEntity
import com.example.gekonapp.data.selectedConvectorDataBase.repositoryImpl.SelectedConvectorRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val convectorRepositoryImpl: ConvectorRepositoryImpl,
    private val selectedRepositoryImpl: SelectedConvectorRepositoryImpl
) : ViewModel() {

    private var model: MutableLiveData<String> = MutableLiveData("")
    var highList: MutableLiveData<Array<String>> = MutableLiveData(arrayOf())
    var weightList: MutableLiveData<Array<String>> = MutableLiveData(arrayOf())
    var lengthList: MutableLiveData<Array<String>> = MutableLiveData(arrayOf())

    var selectedHigh: MutableLiveData<String> = MutableLiveData()
    var selectedWeight: MutableLiveData<String> = MutableLiveData()
    var selectedLength: MutableLiveData<String> = MutableLiveData()

    private var selectedLattice: MutableLiveData<String> = MutableLiveData("")
    private var selectedColor: MutableLiveData<String> = MutableLiveData("")
    var article: MutableLiveData<String> = MutableLiveData("")
    var convector: MutableLiveData<ConvectorEntity> = MutableLiveData()
    var number: MutableLiveData<Int> = MutableLiveData(0)
    var count: MutableLiveData<Int> = MutableLiveData(0)


    // инициализируем модель конвектора
    fun addModel(models: String) {
        when (models) {
            "Eco" -> {
                model.value = "GETL"
                choiceHigh()
            }

            "Vent" -> {
                model.value = "GDTL"
                choiceHigh()
            }

            "Level F" -> {
                model.value = "GLUF"
                choiceHigh()
            }

            "Level W" -> {
                model.value = "GLUW"
                choiceHigh()
            }
        }
    }

    // выбираем высоту ширину и длину
    private fun choiceHigh() {
        when (model.value) {
            "GETL" -> {
                highList.value = arrayOf("008", "009", "011", "014", "019")
                lengthList.value = arrayOf(
                    "060",
                    "070",
                    "080",
                    "090",
                    "100",
                    "110",
                    "120",
                    "130",
                    "140",
                    "150",
                    "160",
                    "170",
                    "180",
                    "190",
                    "200",
                    "210",
                    "220",
                    "230",
                    "240",
                    "250",
                    "260",
                    "270",
                    "280",
                    "290",
                    "300",
                    "310",
                    "320",
                    "330",
                    "340",
                    "350",
                    "360",
                    "370",
                    "380",
                    "390",
                    "400",
                    "410",
                    "420",
                    "430",
                    "440",
                    "450",
                    "460",
                    "470",
                    "480",
                    "490"
                )
                weightList.value = arrayOf(
                    "18",
                    "23",
                    "30",
                    "38"
                )
            }

            "GDTL" -> {
                highList.value = arrayOf("008", "009", "011", "014", "019")
                weightList.value = arrayOf(
                    "23",
                    "30",
                    "38"
                )
                lengthList.value = arrayOf(
                    "090",
                    "100",
                    "110",
                    "120",
                    "130",
                    "140",
                    "150",
                    "160",
                    "170",
                    "180",
                    "190",
                    "200",
                    "210",
                    "220",
                    "230",
                    "240",
                    "250",
                    "260",
                    "270",
                    "280",
                    "290",
                    "300",
                    "310",
                    "320",
                    "330",
                    "340",
                    "350",
                    "360",
                    "370",
                    "380",
                    "390",
                    "400",
                    "410",
                    "420",
                    "430",
                    "440",
                    "450",
                    "460",
                    "470",
                    "480",
                    "490"
                )
            }

            "GLUF" -> {
                highList.value = arrayOf("008", "013", "018", "023", "028", "035")
                weightList.value = arrayOf(
                    "13",
                    "18",
                    "23"
                )
                lengthList.value = arrayOf(
                    "040",
                    "050",
                    "060",
                    "070",
                    "080",
                    "090",
                    "100",
                    "110",
                    "120",
                    "130",
                    "140",
                    "150",
                    "160",
                    "170",
                    "180",
                    "190",
                    "200",
                    "210",
                    "220",
                    "230",
                    "240",
                    "250",
                    "260",
                    "270",
                    "280",
                    "290",
                    "300"
                )
            }

            "GLUW" -> {
                highList.value = arrayOf("013", "020", "030", "040", "050")
                weightList.value = arrayOf(
                    "08",
                    "13",
                    "18",
                    "23"
                )
                lengthList.value = arrayOf(
                    "040\n",
                    "050\n",
                    "060\n",
                    "070\n",
                    "080\n",
                    "090\n",
                    "100\n",
                    "110\n",
                    "120\n",
                    "130\n",
                    "140\n",
                    "150\n",
                    "160\n",
                    "170\n",
                    "180\n",
                    "190\n",
                    "200\n",
                    "210\n",
                    "220\n",
                    "230\n",
                    "240\n"
                )
            }
        }
    }

    // выбираем габариты
    fun selectedDimensions(selectedDimensions: String, dimensions: String) {
        when (dimensions) {
            "high" -> selectedHigh.value = selectedDimensions
            "weight" -> selectedWeight.value = selectedDimensions
            "length" -> selectedLength.value = selectedDimensions
        }
        Log.d("TAG", selectedHigh.value.toString())

    }

    fun initTypeLattice(lattice: String) {
        selectedLattice.value = lattice
    }


    fun initSelectedColor(color: String) {
        selectedColor.value = color
    }

    fun initCount(countConvector: String) {
        count.value = countConvector.toInt()
    }

    fun result(context: Context) {
        val resultHigh = selectedHigh.value?.toFloat()?.div(1000)
        val resultLength = selectedLength.value?.toFloat()?.div(100)
        val length = resultLength.toString().replace(".", "")
        article.value =
            "${model.value}${resultHigh}${length}0${selectedWeight.value?.toInt()}/${selectedLattice.value}${selectedColor.value}/NV"

        Log.d("TAG", article.value.toString())

        if (model.value!!.isNotEmpty()
            && resultLength.toString().isNotEmpty()
            && selectedWeight.value.toString().isNotEmpty()
            && resultHigh.toString().isNotEmpty()
            && selectedLattice.value.toString().isNotEmpty()
            && selectedColor.value.toString().isNotEmpty()
        ) {

            viewModelScope.launch(Dispatchers.IO) {
                val entity = convectorRepositoryImpl.getByArticle(article.value.toString())

                withContext(Dispatchers.Main) {
                    convector.value = entity
                }
            }
        } else {
            Toast.makeText(context, "Введите все значения конвектора", Toast.LENGTH_SHORT).show()
        }

    }

    fun addSelectedConvector() {

        viewModelScope.launch(Dispatchers.IO) {
            selectedRepositoryImpl.addSelectedConvector(
                SelectedConvectorEntity(
                    number = number.value!!,
                    article = convector.value?.article!!,
                    name = convector.value?.name!!,
                    power = convector.value?.power!!,
                    price = convector.value?.price!!,
                    count = count.value!!
                )
            )
            withContext(Dispatchers.Main) {
                number.value = number.value!! + 1
            }
        }
    }


}