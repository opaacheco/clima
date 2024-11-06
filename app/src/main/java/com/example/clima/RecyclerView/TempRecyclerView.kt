package com.example.clima.RecyclerView

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.clima.Classes.WeatherResponse
import com.example.clima.R

class TempRecyclerView(
    private val context: Context,
    var temp:WeatherResponse
) : RecyclerView.Adapter<TempRecyclerView.MyViewHolder>() {
    override fun onCreateViewHolder(
        @NonNull parent: ViewGroup,
        viewType: Int
    ): TempRecyclerView.MyViewHolder {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.item_temp, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: TempRecyclerView.MyViewHolder, position: Int) {
        holder.tempText.text = temp.main.temp.toString()
        Log.v("tem", temp.main.temp_min.toString())
        holder.descriptionText.text = temp.weather[0].description
        val url = "https://openweathermap.org/img/wn/"+temp.weather[0].icon+"@2x.png"
        Glide.with(holder.imageView.context).load(url).into(holder.imageView)

    }

    override fun getItemCount(): Int {
        return 1
    }

    class MyViewHolder(itemView:View): RecyclerView.ViewHolder(itemView){
        val tempText: TextView = itemView.findViewById(R.id.temp)
        val descriptionText: TextView = itemView.findViewById(R.id.description)
        val imageView: ImageView = itemView.findViewById(R.id.tempIcon)
    }
}