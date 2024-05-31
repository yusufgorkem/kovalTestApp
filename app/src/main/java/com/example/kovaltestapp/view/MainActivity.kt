package com.example.kovaltestapp.view

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kovaltestapp.utils.Constants.options
import com.example.kovaltestapp.R
import com.example.kovaltestapp.database.OptionDb
import com.example.kovaltestapp.databinding.ActivityMainBinding
import com.example.kovaltestapp.model.Option
import com.example.kovaltestapp.view.adapter.RecyclerViewAdapter
import com.example.kovaltestapp.viewmodel.MainViewModel
import com.google.android.material.bottomsheet.BottomSheetBehavior


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: RecyclerViewAdapter
    private lateinit var viewModel: MainViewModel
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<*>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        enableEdgeToEdge()
        setContentView(view)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        val optionDb: OptionDb? = OptionDb.getOptionDb(this)
        if ((optionDb?.optionDao()?.getAllOptions() as ArrayList<Option>).size == 0) {
            for (option in options) {
                optionDb.optionDao().insert(option)
            }
        }
        val optionsFromDb = optionDb.optionDao().getAllOptions()
        adapter = RecyclerViewAdapter(this, optionsFromDb as ArrayList<Option>)
        observeAnimation()
        setUi(optionsFromDb)
    }

    private fun observeAnimation() {
        viewModel.animate.observe(this) { animate ->
            if (animate) {
                startAnimation()

                viewModel.animate.value = false
            }
        }
    }

    private fun startAnimation() {
        binding.lottieAnimation.visibility = View.VISIBLE
        binding.lottieAnimation.playAnimation()
    }

    private fun setUi(optionsFromDb: List<Option>) {
        fun setAnimation() {
            viewModel.startAnimation()
        }

        fun setImage33() {
            binding.image33.setImageResource(R.drawable.image33)
        }

        fun setDanger(optionsFromDb: List<Option>) {
            var problemNumber = 0

            binding.dangerCircle.setImageResource(R.drawable.danger_circle)
            for (option in optionsFromDb) {
                problemNumber += option.problem
            }
            val problemText: String = if (problemNumber < 2) {
                "Problem"
            } else {
                "Problems"
            }
            binding.problems.text = "$problemNumber $problemText"
        }

        fun setScanRectangle() {
            binding.scanRectangle.image.setImageResource(R.drawable.device_scan)
            binding.scanRectangle.head.setText(R.string.device_scan)
            binding.scanRectangle.button.setText(R.string.scan)
        }

        fun setVirusesRectangle() {
            binding.checkVirusesRectangle.image.setImageResource(R.drawable.virus_white)
            binding.checkVirusesRectangle.head.setText(R.string.check_for_viruses)
            binding.checkVirusesRectangle.button.setText(R.string.check)
        }

        fun showBottomSheet() {
            bottomSheetBehavior = BottomSheetBehavior.from(binding.bottomSheet)
            bottomSheetBehavior.peekHeight = getScreenHeight() / 3
            binding.recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
            binding.recyclerView.adapter = adapter
        }


        setAnimation()
        setImage33()
        setDanger(optionsFromDb)
        setScanRectangle()
        setVirusesRectangle()
        showBottomSheet()
    }

    private fun getScreenHeight() : Int {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            val windowMetrics = windowManager.currentWindowMetrics
            val rect = windowMetrics.bounds
            rect.bottom
        } else {
            resources.displayMetrics.heightPixels
        }
    }
}