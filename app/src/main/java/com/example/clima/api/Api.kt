package com.example.clima.api


import android.os.Handler
import android.os.Looper
import android.util.Log
import com.example.clima.Classes.Cord
import com.example.clima.Classes.WeatherResponse
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.Request
import java.util.concurrent.Executors

class Api {

    val client = OkHttpClient()
    val gson = Gson()
    val execut = Executors.newSingleThreadExecutor()
    val handler = Handler(Looper.getMainLooper())
    var apiCorde = ApiCorde()

    fun previsaoTempo(cityName:String, callback: ApiCallback){
        apiCorde.getLatAndLong(cityName) { cord ->
            var url = "https://api.openweathermap.org/data/2.5/weather?lat=${cord.lat}&lon=${cord.lon}&appid=58539b3742a4adb28a6df21895f35fa6&units=metric"
            val requestTempo = Request.Builder()
                .url(url)
                .get()
                .addHeader("accept", "application/json")
                .addHeader(
                    "Authorization",
                    "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI5M2ZhZTMyNjEzYjJmNDE1NGJjNDY4Y2QxMmM1Zjk4MSIsIm5iZiI6MTcyNzcxMjE3NS4yOTU2NjYsInN1YiI6IjY2ZmFiZTg3ODA3Y2Q1MWMxN2YxOTQ2MSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.XK4kaiZxMZ9xKrtQF5YSh5HwzuruRmHs75_1kGPBcEw"
                )
                .build()

            execut.execute{
                val response = client.newCall(requestTempo).execute()
                val dataTratado = response.body?.string()
                val weatherResponse = gson.fromJson(dataTratado, WeatherResponse::class.java)
                handler.post {
                    callback.onSuccess(weatherResponse)
                }
            }
        }
    }
}