package com.brunor.feup.group1.ticketsystem.model

import com.google.gson.annotations.SerializedName
import java.util.*

data class Performance (
    @SerializedName("_id") val id:String,
    @SerializedName("Created_date") val created_date: Date,
    @SerializedName("showName") val showName: String,
    @SerializedName("showDate") val showDate: String,
    @SerializedName("showPrice") var showPrice: String,
    @SerializedName("showLocation") val showLocation: String,
    @SerializedName("thumbnailIcon") val thumbnailIcon: String)