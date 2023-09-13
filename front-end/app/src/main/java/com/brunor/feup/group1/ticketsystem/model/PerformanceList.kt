package com.brunor.feup.group1.ticketsystem.model

import com.google.gson.annotations.SerializedName

data class PerformanceList (
    @SerializedName("success") val id:String,
    @SerializedName("routes") val performances: List<Performance>
)