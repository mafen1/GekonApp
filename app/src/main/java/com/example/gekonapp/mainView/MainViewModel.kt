package com.example.gekonapp.mainView

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    private var model: MutableLiveData<String> = MutableLiveData("")
    var highList: MutableLiveData<List<String>> = MutableLiveData(listOf())
    var weightList: MutableLiveData<List<String>> = MutableLiveData(listOf())
    var lengthList: MutableLiveData<List<String>> = MutableLiveData(listOf())

    private var selectedHigh: MutableLiveData<String> = MutableLiveData("")
    private var selectedWeight: MutableLiveData<String> = MutableLiveData("")
    private var selectedLength: MutableLiveData<String> = MutableLiveData("")

    private var selectedLattice:MutableLiveData<String> = MutableLiveData("")
    private var selectedColor:MutableLiveData<String> = MutableLiveData("")
     var result:MutableLiveData<String> = MutableLiveData("")

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
    fun selectedDimensions(selectedDimensions: String, dimensions: String){
        when(dimensions){
            "high" -> selectedHigh.value = selectedDimensions
            "weight" -> selectedWeight.value = selectedDimensions
            "length" -> selectedLength.value = selectedDimensions
        }
    }

    fun initTypeLattice(lattice: String){
        selectedLattice.value = lattice
    }

    fun initSelectedColor(color:String){
        selectedColor.value = color
    }

    fun result(){
        result.value = "${model.value}${selectedHigh.value}${selectedLength.value}${selectedWeight.value}/${selectedLattice.value}${selectedColor.value}/NV"
    }
}