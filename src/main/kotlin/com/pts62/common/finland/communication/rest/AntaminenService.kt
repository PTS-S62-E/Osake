package com.pts62.common.finland.communication.rest

import com.google.gson.Gson
import com.pts62.common.finland.communication.IQueueSubscribeCallback
import com.pts62.common.finland.communication.rest.api.CarResponse
import com.pts62.common.finland.communication.rest.api.GetVehicleByLicensePlateResponse
import com.pts62.common.finland.communication.rest.api.JwtRequestParameters
import com.pts62.common.fromJson
import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import javax.ejb.Stateless

@Stateless
class AntaminenService : RestService() {

    private var jwtToken: String? = null

    override fun getDefaultConfig() =
            object : IServiceConfiguration {
                override fun getServiceAddress() = "http://192.168.24.100:8082/rekening-administratie/api"
                override fun getServiceCredential() = "proftaak@example.com"
                override fun getServicePassword() = "proftaak"
            }


    fun retrieveJwtToken(forceRefresh: Boolean = false): String {
        if (this.jwtToken != null) {
            return this.jwtToken!!
        }

        val jwtRequestParameters = JwtRequestParameters(this.getConfig().getServiceCredential(), this.getConfig().getServicePassword())

        val postBody = RequestBody.create(this.mediaTypeJson, Gson().toJson(jwtRequestParameters))
        val antaminenLoginUrl = "${this.getConfig().getServiceAddress()}/accounts/login"
        val request = Request
                .Builder()
                .url(antaminenLoginUrl)
                .post(postBody)
                .header("Accept", "application/json")
                .build()

        // Check je VPN verbinding als dit niet werkt
        val requestResult = this.httpClient.newCall(request).execute()!!
        val bodyString = requestResult.body()!!.string()!!
        val responseMap = Gson().fromJson<Map<String, Any>>(bodyString)

        val token = responseMap["token"] as String
        val owner = responseMap["owner"]
        this.jwtToken = token
        return token
    }

    fun getVehicles(): List<CarResponse> {
        val antaminenCarUrl = "${this.getConfig().getServiceAddress()}/accounts/cars"
        val request = Request
                .Builder()
                .url(antaminenCarUrl)
                .header("Authorization", this.retrieveJwtToken())
                .build()

        val requestResult = this.httpClient.newCall(request).execute()!!
        val bodyString = requestResult.body()!!.string()!!
        return Gson().fromJson(bodyString)
    }

    fun getVehicleByLicensePlate(licensePlate: String): GetVehicleByLicensePlateResponse {
        // Example: xx-yy-69
        val antaminenCarUrl = "${this.getConfig().getServiceAddress()}/vehicles/licensePlate/$licensePlate/history/ownership"
        val request = Request
                .Builder()
                .url(antaminenCarUrl)
                .header("Authorization", this.retrieveJwtToken())
                .build()

        val requestResult = this.httpClient.newCall(request).execute()!!
        val bodyString = requestResult.body()!!.string()!!
        return Gson().fromJson(bodyString)
    }
}
