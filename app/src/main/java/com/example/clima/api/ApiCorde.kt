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


class ApiCorde {

    val client = OkHttpClient()
    val gson = Gson()
    val execut = Executors.newSingleThreadExecutor()
    val handler = Handler(Looper.getMainLooper())

    fun getLatAndLong(cityNome:String, callback: (Cord) -> Unit) {
        var url =
            "https://api.openweathermap.org/geo/1.0/direct?q=" + cityNome + "&limit=1&appid=58539b3742a4adb28a6df21895f35fa6"
        val requestCords = Request.Builder()
            .url(url)
            .get()
            .addHeader("accept", "application/json")
            .addHeader(
                "Authorization",
                "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI5M2ZhZTMyNjEzYjJmNDE1NGJjNDY4Y2QxMmM1Zjk4MSIsIm5iZiI6MTcyNzcxMjE3NS4yOTU2NjYsInN1YiI6IjY2ZmFiZTg3ODA3Y2Q1MWMxN2YxOTQ2MSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.XK4kaiZxMZ9xKrtQF5YSh5HwzuruRmHs75_1kGPBcEw"
            )
            .build()
        execut.execute {
            val response = client.newCall(requestCords).execute()
            val dataTratado = response.body?.string()
            val cords = gson.fromJson(dataTratado, Array<Cord>::class.java)
            Log.v("dataTratado", cords.toString())

            handler.post {
                callback(cords.get(0))
            }
        }
    }
}