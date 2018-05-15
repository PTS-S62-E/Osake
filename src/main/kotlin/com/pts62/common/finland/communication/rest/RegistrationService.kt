package com.pts62.common.finland.communication.rest

import io.github.cdimascio.dotenv.Dotenv
import okhttp3.MediaType
import okhttp3.OkHttpClient

class RegistrationService {

    private val mediaTypeJson = MediaType.parse("application/json; charset=utf-8")

    fun getRegistratieUrl() = Dotenv.load()["HTTP_REGISTRATIE_URL"]

    private fun getHttpClient() =
            OkHttpClient
                    .Builder()
                    .build()!!
    
    fun getVehicles() {
        val vehicleUrls = "${this.getRegistratieUrl()}/vehicle"

    }
    
}