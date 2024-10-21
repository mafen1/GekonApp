package com.example.gekonapp.presentation.convectorFragment.viewmodel

import android.content.Context
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
    var highList: MutableLiveData<List<String>> = MutableLiveData(listOf())
    var weightList: MutableLiveData<List<String>> = MutableLiveData(listOf())
    var lengthList: MutableLiveData<List<String>> = MutableLiveData(listOf())

    private var selectedHigh: MutableLiveData<Float> = MutableLiveData()
    private var selectedWeight: MutableLiveData<Float> = MutableLiveData()
    private var selectedLength: MutableLiveData<Float> = MutableLiveData()

    private var selectedLattice: MutableLiveData<String> = MutableLiveData("")
    private var selectedColor: MutableLiveData<String> = MutableLiveData("")
    var article: MutableLiveData<String> = MutableLiveData("")
    var convector: MutableLiveData<ConvectorEntity> = MutableLiveData()
    var number: MutableLiveData<Int> = MutableLiveData(4)


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
                highList.value = listOf("008", "009", "011", "014", "019")
                lengthList.value = listOf(
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
                weightList.value = listOf(
                    "18",
                    "23",
                    "30",
                    "38"
                )
            }

            "GDTL" -> {
                highList.value = listOf("008", "009", "011", "014", "019")
                weightList.value = listOf(
                    "23",
                    "30",
                    "38"
                )
                lengthList.value = listOf(
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
                highList.value = listOf("008", "013", "018", "023", "028", "035")
                weightList.value = listOf(
                    "13",
                    "18",
                    "23"
                )
                lengthList.value = listOf(
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
                highList.value = listOf("013", "020", "030", "040", "050")
                weightList.value = listOf(
                    "08",
                    "13",
                    "18",
                    "23"
                )
                lengthList.value = listOf(
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
            "high" -> selectedHigh.value = selectedDimensions.toFloat()
            "weight" -> selectedWeight.value = selectedDimensions.toFloat()
            "length" -> selectedLength.value = selectedDimensions.toFloat()
        }
    }

    fun initTypeLattice(lattice: String) {
        selectedLattice.value = lattice
    }

    fun initSelectedColor(color: String) {
        selectedColor.value = color
    }

    fun result(context: Context) {
        val resultHigh = selectedHigh.value?.div(1000)
        val resultLength = selectedLength.value?.div(100)
        val length = resultLength.toString().replace(".", "")
        article.value =
            "${model.value}${resultHigh}${length}0${selectedWeight.value?.toInt()}/${selectedLattice.value}${selectedColor.value}/NV"

        if (model.value!!.isNotEmpty()
            && resultLength.toString().isNotEmpty()
            && selectedWeight.value.toString().isNotEmpty()
            && resultHigh.toString().isNotEmpty()
            && selectedLattice.value.toString().isNotEmpty()
            && selectedColor.value.toString().isNotEmpty()
        ) {

            viewModelScope.launch(Dispatchers.IO) {
                val entity = convectorRepositoryImpl.getByArticle(article.value.toString())


                val selectedConvector = SelectedConvectorEntity(
                    article = entity.article.toString(),
                    name = entity.name.toString(),
                    power = entity.power!!.toInt(),
                    price = entity.price.toString(),
                    count = number.value!!,
                    number = number.value!!
                )

                selectedRepositoryImpl.addSelectedConvector(
                    selectedConvector
                )
                withContext(Dispatchers.Main) {
                    convector.value = entity
                }
            }
        } else {
            Toast.makeText(context, "Введите все значения конвектора", Toast.LENGTH_SHORT).show()
        }

    }




}