package com.example.kovaltestapp.view

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kovaltestapp.utils.Constants.options
import com.example.kovaltestapp.R
import com.example.kovaltestapp.database.OptionDb
import com.example.kovaltestapp.databinding.ActivityMainBinding
import com.example.kovaltestapp.model.Option
import com.example.kovaltestapp.view.adapter.RecyclerViewAdapter
import com.example.kovaltestapp.viewmodel.MainViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog


class MainActivity : AppCompatActivity() {
    private  lateinit var binding: ActivityMainBinding
    private lateinit var dialog: BottomSheetDialog
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: RecyclerViewAdapter
    private lateinit var viewModel: MainViewModel

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
        adapter = RecyclerViewAdapter(this, optionDb.optionDao().getAllOptions() as ArrayList<Option>)
        observeAnimation()
        setUi()
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

    private fun setUi() {
        fun setAnimation() {
            viewModel.startAnimation()
        }

        fun setImage33() {
            binding.image33.setImageResource(R.drawable.image33)
        }

        fun setDangerCircle() {
            binding.dangerCircle.setImageResource(R.drawable.danger_circle)
        }

        fun setScanRectangle() {
            binding.scanRectangle.image.setImageResource(R.drawable.device_scan)
            binding.scanRectangle.head.setText(R.string.device_scan)
            binding.scanRectangle.button.setText(R.string.scan)
            binding.scanRectangle.button.setOnClickListener { showBottomSheet() }
        }

        fun setVirusesRectangle() {
            binding.checkVirusesRectangle.image.setImageResource(R.drawable.virus_white)
            binding.checkVirusesRectangle.head.setText(R.string.check_for_viruses)
            binding.checkVirusesRectangle.button.setText(R.string.check)
            binding.checkVirusesRectangle.button.setOnClickListener { showBottomSheet() }
        }

        setAnimation()
        setImage33()
        setDangerCircle()
        setScanRectangle()
        setVirusesRectangle()
    }

    private fun showBottomSheet() {
        val dialogView = layoutInflater.inflate(R.layout.bottom_sheet, null)
        dialog = BottomSheetDialog(this)
        dialog.setContentView(dialogView)
        recyclerView = dialogView.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = adapter
        dialog.show()
    }
}