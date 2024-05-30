package com.example.kovaltestapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {
    val animate: MutableLiveData<Boolean> = MutableLiveData(false)

    fun startAnimation() {
        animate.value = true
    }
}