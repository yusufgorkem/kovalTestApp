package com.example.kovaltestapp.utils

import com.example.kovaltestapp.R
import com.example.kovaltestapp.model.Option

object Constants {

    private val option1 = Option(image = R.drawable.info, head = R.string.device_info, body = R.string.show_about_phone, problem = 0)
    private val option2 = Option(
        image = R.drawable.rotate_angle,
        head = R.string.calibration_of_sensors,
        body = R.string.show_about,
        problem = 1
    )
    private val option3 = Option(image = R.drawable.scan, head = R.string.app_monitoring, body = R.string.show_about, problem = 0)
    private val option4 = Option(image = R.drawable.virus_blue, head = R.string.anti_virus, body = R.string.show_about, problem = 2)
    private val option5 = Option(
        image = R.drawable.library,
        head = R.string.device_memory_information,
        body = R.string.show_about,
        problem = 0
    )
    private val option6 = Option(image = R.drawable.file_smile, head = R.string.file_manager, body = R.string.show_about, problem = 3)
    private val option7 = Option(
        image = R.drawable.battery_full,
        head = R.string.battery_info,
        body = R.string.show_about,
        problem = 6
    )

    val options = arrayListOf(option1, option2, option3, option4, option5, option6, option7)
}