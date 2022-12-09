package com.example.wonders

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class DestinationRecycleAdapter(val context: Context, val destinationArray: List<Destination>) : RecyclerView.Adapter<DestinationRecycleAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.destination_list,parent,false)

        return ViewHolder(itemView)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val destination = destinationArray[position]

        //holder.picture.setImageDrawable(holder.picture.context.getDrawable(Destination.picture))
        holder.countryView.text = destination.country
        holder.placeView.text = destination.place
        holder.infoView.text = destination.info
    }

    override fun getItemCount(): Int {
        return destinationArray.size
    }

    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        //val picture : ImageView = itemView.findViewById(R.id.imageOfPlace)
        val countryView : TextView = itemView.findViewById(R.id.countryTextView)
        val placeView : TextView = itemView.findViewById(R.id.placeTextView)
        val infoView : TextView = itemView.findViewById(R.id.infoTextView)


    }

}