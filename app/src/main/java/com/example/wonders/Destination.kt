package com.example.wonders

import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.GeoPoint


data class Destination(@DocumentId var documentId: String? = null,
                       var country: String? = null,
                       var pictureUrl: String? = null,
                       var place: String? = null,
                       var info: String? = null,
                       val geoPoint: GeoPoint? = null
)

