package com.example.wonders

object DataManager {

    val destinations = mutableListOf<Destination>()

    init{
        createMockData()
    }

    fun createMockData(){
        destinations.add(Destination(R.drawable.a,"China", "The Great Wall of China", ".."))
        destinations.add(Destination(R.drawable.b,"Mexico", "Chichén Itzá", ".."))
        destinations.add(Destination(R.drawable.c,"Jordan", "Petra", ".."))
        destinations.add(Destination(R.drawable.d,"Peru", "Machu Picchu", ".."))
        destinations.add(Destination(R.drawable.e,"Brazil", "Christ The Redeemer", ".."))
        destinations.add(Destination(R.drawable.f,"Italia", "Colosseum", ".."))


    }
}