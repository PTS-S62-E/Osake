package com.pts62.common.finland.communication.rest

import com.google.gson.Gson
import com.pts62.common.finland.communication.rest.api.CarResponse
import com.pts62.common.finland.communication.rest.api.GetVehicleByLicensePlateResponse
import com.pts62.common.finland.communication.rest.api.JwtRequestParameters
import com.pts62.common.fromJson
import io.github.cdimascio.dotenv.Dotenv
import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import javax.ejb.Stateless

@Stateless
class AntaminenService {

    private val mediaTypeJson = MediaType.parse("application/json; charset=utf-8")

    private val serviceConfig:IServiceConfiguration by lazy {
        object : IServiceConfiguration {
            override fun getServiceAddress() = "http://192.168.24.100:8082/rekening-administratie/api"
            override fun getServiceCredential() = "proftaak@example.com"
            override fun getServicePassword() = "proftaak"
        }
    }


    private fun getHttpClient() =
            OkHttpClient
                    .Builder()
                    .build()!!

    private var jwt_token: String? = null

    fun retrieveJwtToken(forceRefresh: Boolean = false): String {
        if (this.jwt_token != null) {
            return this.jwt_token!!
        }

        val jwtRequestParameters = JwtRequestParameters(this.serviceConfig.getServiceCredential(), this.serviceConfig.getServicePassword())

        val postBody = RequestBody.create(this.mediaTypeJson, Gson().toJson(jwtRequestParameters))
        val antaminenLoginUrl = "${this.serviceConfig.getServiceAddress()}/accounts/login"
        val request = Request
                .Builder()
                .url(antaminenLoginUrl)
                .post(postBody)
                .header("Accept", "application/json")
                .build()

        // Check je VPN verbinding als dit niet werkt
        val requestResult = this.getHttpClient().newCall(request).execute()!!
        val bodyString = requestResult.body()!!.string()!!
        val responseMap = Gson().fromJson<Map<String, Any>>(bodyString)

        val token = responseMap["token"] as String
        val owner = responseMap["owner"]
        this.jwt_token = token
        return token
    }

    fun getVehicles(): List<CarResponse> {
        val antaminenCarUrl = "${this.serviceConfig.getServiceAddress()}/accounts/cars"
        val request = Request
                .Builder()
                .url(antaminenCarUrl)
                .header("Authorization", this.retrieveJwtToken())
                .build()

        val requestResult = this.getHttpClient().newCall(request).execute()!!
        val bodyString = requestResult.body()!!.string()!!
        return Gson().fromJson(bodyString)
    }

    fun getVehicleByLicensePlate(licensePlate: String): GetVehicleByLicensePlateResponse {

        // Example: xx-yy-69
        val antaminenCarUrl = "${this.serviceConfig.getServiceAddress()}/vehicles/licensePlate/$licensePlate/history/ownership"
        val request = Request
                .Builder()
                .url(antaminenCarUrl)
                .header("Authorization", this.retrieveJwtToken())
                .build()

        val requestResult = this.getHttpClient().newCall(request).execute()!!
        val bodyString = requestResult.body()!!.string()!!
        val r = Gson().fromJson(bodyString) as GetVehicleByLicensePlateResponse
        return r
    }
}

fun main(args:Array<String>) {
    AntaminenService().getVehicleByLicensePlate("xx-yy-69")
}
