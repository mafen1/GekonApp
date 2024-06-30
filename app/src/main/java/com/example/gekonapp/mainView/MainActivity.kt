package com.example.gekonapp.mainView

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.RadioButton
import android.widget.Spinner
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.gekonapp.databinding.ActivityMainBinding
import com.example.gekonapp.mainView.database.DatabaseBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    val mainViewModel: MainViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
        logAssetsContents()

    }

    private fun initView() {
        initRadioGroup()
        initRadioGroup2()
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
            mainViewModel.result()
            binding.result.text = mainViewModel.result.value

            val db = DatabaseBuilder.getInstance(this.applicationContext)
            lifecycleScope.launch(Dispatchers.IO){
                val dao = db.convectorDao()
                dao.getAll()
                val d = dao.getAll()
                Log.d("TAG", d.toString())
                logAssetsContents()
            }
        }


    }


    private fun initSpinner() {
        setupSpinner(binding.spinner, mainViewModel.highList.value!!, "high")
        setupSpinner(binding.spinner2, mainViewModel.lengthList.value!!, "length")
        setupSpinner(binding.spinner3, mainViewModel.weightList.value!!, "weight")
    }

    private fun setupSpinner(spinner: Spinner, dataList: List<String>, dimensionType: String) {
        val spinnerAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, dataList)
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = spinnerAdapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                mainViewModel.selectedDimensions(parent?.selectedItem.toString(), dimensionType)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                mainViewModel.selectedDimensions(dataList[0], dimensionType)
            }
        }
    }

    private fun initRadioGroup(){
        binding.radioGroup.setOnCheckedChangeListener{group, checkedId ->
            val selectedRadioButton = findViewById<RadioButton>(checkedId)
            mainViewModel.initTypeLattice(selectedRadioButton.tag.toString())
            Log.d("TAG", selectedRadioButton.tag.toString())
        }
    }
    private fun initRadioGroup2(){
        binding.radioGroup2.setOnCheckedChangeListener{group, checkedId ->
            val selectedRadioButton = findViewById<RadioButton>(checkedId)
            mainViewModel.initSelectedColor(selectedRadioButton.tag.toString())
        }
    }

    private fun logAssetsContents() {
        try {
            val assetManager = assets
            val files = assetManager.list("") ?: return
            for (file in files) {
                Log.d("TAG", "File: $file")
            }
            val databaseFiles = assetManager.list("database") ?: return
            for (file in databaseFiles) {
                Log.d("TAG", "Database File: $file")
            }
        } catch (e: Exception) {
            Log.e("AssetsLog", "Error listing assets", e)
        }
    }
}


