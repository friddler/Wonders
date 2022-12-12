package com.example.wonders

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide


class DestinationRecycleAdapter(val context: Context, val destinationArray: List<Destination>) : RecyclerView.Adapter<DestinationRecycleAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.destination_list,parent,false)

        return ViewHolder(itemView)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val destination = destinationArray[position]

            Glide.with(context)
                .load(destination.pictureUrl)
                .into(holder.picture)

        holder.country.text = destination.country
        holder.place.text = destination.place
        holder.info.text = destination.info
    }

    override fun getItemCount(): Int {
        return destinationArray.size
    }

    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        val picture : ImageView = itemView.findViewById(R.id.imageOfPlace)
        val country : TextView = itemView.findViewById(R.id.countryTextView)
        val place : TextView = itemView.findViewById(R.id.placeTextView)
        val info : TextView = itemView.findViewById(R.id.infoTextView)


    }

}