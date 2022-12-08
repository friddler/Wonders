package com.example.wonders

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore


class DestinationRecycleAdapter(val context: Context, private val destinationList: MutableList<Destination>) : RecyclerView.Adapter<DestinationRecycleAdapter.ViewHolder>() {

    val db = FirebaseFirestore.getInstance()
    val collectionRef = db.collection("destinations")

    val listener = collectionRef.addSnapshotListener{ snapshot, exception ->
        if(exception != null){
            return@addSnapshotListener
        }
        destinationList.clear()
        for (document in snapshot!!) {
            val destination = document.toObject<Destination>(Destination::class.java)
            destinationList.add(destination)
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.destination_list,parent,false)

        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val destination = destinationList[position]

        //holder.picture.setImageDrawable(holder.picture.context.getDrawable(destination.picture))
        holder.countryView.text = destination.country
        holder.placeView.text = destination.place
        holder.infoView.text = destination.info
    }

    override fun getItemCount(): Int {
        return destinationList.size
    }

    override fun onViewRecycled(holder: ViewHolder) {
        super.onViewRecycled(holder)
        listener.remove()
    }

    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        //val picture : ImageView = itemView.findViewById(R.id.imageView)
        val countryView : TextView = itemView.findViewById(R.id.countryTextView)
        val placeView : TextView = itemView.findViewById(R.id.placeTextView)
        val infoView : TextView = itemView.findViewById(R.id.infoTextView)


    }

}