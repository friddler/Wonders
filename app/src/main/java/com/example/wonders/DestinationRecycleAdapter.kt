package com.example.wonders

import android.content.Context
import android.content.Intent
import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.firebase.firestore.DocumentId


class DestinationRecycleAdapter(private val context: Context, private val destinationArray: List<Destination>) : RecyclerView.Adapter<DestinationRecycleAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.destination_list,parent,false)

        return ViewHolder(itemView)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val destination = destinationArray[position]

        holder.country.text = destination.country
        holder.place.text = destination.place

        Glide.with(context)
            .load(destination.pictureUrl)
            .into(holder.picture)

        holder.apply {
            picture.setOnClickListener {
                val intent = Intent(context, ShowInfoActivity::class.java)
                intent.putExtra("destination_info", destination.info)
                intent.putExtra("destination_country", destination.place)
                intent.putExtra("destination_picture",destination.pictureUrl)
                context.startActivity(intent)

            }
        }
        holder.apply {
            mapImage.setOnClickListener {
                val intent = Intent(context, MapsActivity::class.java)
                context.startActivity(intent)

            }
        }
    }

    override fun getItemCount(): Int {
        return destinationArray.size
    }

    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        val picture : ImageView = itemView.findViewById(R.id.imageOfPlace)
        val country : TextView = itemView.findViewById(R.id.countryTextView)
        val place : TextView = itemView.findViewById(R.id.placeTextView)
        val mapImage : ImageView = itemView.findViewById(R.id.mapImageView)



    }

}