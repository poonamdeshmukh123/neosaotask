package com.example.neosaotask.api.api.model

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName



 class EntryModel {

    @SerializedName("API")
    @Expose
    private val api: String? = null

    @SerializedName("Description")
    @Expose
    private val description: String? = null

    @SerializedName("Auth")
    @Expose
    private val auth: String? = null

    @SerializedName("HTTPS")
    @Expose
    private val https: Boolean? = null

    @SerializedName("Cors")
    @Expose
    private val cors: String? = null

    @SerializedName("Link")
    @Expose
    val link: String? = null

    @SerializedName("Category")
    @Expose
     val category: String? = null
}
