package com.example.clima

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.clima.Classes.WeatherResponse
import com.example.clima.RecyclerView.TempRecyclerView
import com.example.clima.api.Api
import com.example.clima.api.ApiCallback

class MainActivity : AppCompatActivity(), ApiCallback {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: TempRecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        recyclerView = findViewById(R.id.viewTemperatura)
        recyclerView.layoutManager = LinearLayoutManager(this)

    }

    override fun onSuccess(cityTemp: WeatherResponse) {
        adapter = TempRecyclerView(this, cityTemp)
        recyclerView.adapter = adapter
        adapter.temp = cityTemp
        adapter.notifyDataSetChanged()
    }

    override fun onFailure(error: String) {
        println("Erro ao buscar dados: $error")
    }

    fun pesquisarTemp(view:View) {
        val cityNome = findViewById<EditText>(R.id.cityNome).text.toString()
        val api = Api()
        api.previsaoTempo(cityNome,this)
    }
}