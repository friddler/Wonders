package com.example.wonders

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import io.grpc.Context

class DestinationRecycleAdapter (private val destinationList : ArrayList<Destination>) : RecyclerView.Adapter<DestinationRecycleAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.destination_list,parent,false)

        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val destination = destinationList[position]

        holder.country.text = destination.country
        holder.place.text = destination.place
        holder.info.text = destination.info
    }

    override fun getItemCount(): Int {
        return destinationList.size
    }

    public class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        val country : TextView = itemView.findViewById(R.id.countryTextView)
        val place : TextView = itemView.findViewById(R.id.placeTextView)
        val info : TextView = itemView.findViewById(R.id.infoTextView)


    }
}