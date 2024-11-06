package com.example.clima.api

import com.example.clima.Classes.WeatherResponse

interface ApiCallback {
    fun onSuccess(cityTemp: WeatherResponse) // Altere para um único WeatherResponse
    fun onFailure(error: String)
}
