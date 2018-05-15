package com.pts62.common.finland.communication.rest

import com.google.gson.Gson
import com.pts62.common.facade.verplaatsing.VehicleFacade
import com.pts62.common.finland.communication.rest.api.AdministrationPostCar
import com.pts62.common.finland.communication.rest.api.JwtRequestParameters
import com.pts62.common.fromJson
import com.pts62.common.toJson
import io.github.cdimascio.dotenv.Dotenv
import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import java.util.*

class AntaminenService {

    private val mediaTypeJson = MediaType.parse("application/json; charset=utf-8")

    fun getAntaminenUrl() = Dotenv.load()["HTTP_ANTAMINEN_URL"]
    fun getAntaminenEmail() = Dotenv.load()["HTTP_ANTAMINEN_EMAIL"]
    fun getAntaminenPassword() = Dotenv.load()["HTTP_ANTAMINEN_PASSWORD"]

    private fun getHttpClient() =
            OkHttpClient
                    .Builder()
                    .build()!!

    private var jwt_token: String? = null

    private fun retrieveJwtToken(forceRefresh: Boolean = false): String {
        if (this.jwt_token != null && !forceRefresh) {
            return this.jwt_token!!
        }

        val jwtRequestParameters = JwtRequestParameters(this.getAntaminenEmail()!!, this.getAntaminenPassword()!!)

        val postBody = RequestBody.create(this.mediaTypeJson, Gson().toJson(jwtRequestParameters))
        val antaminenLoginUrl = "${this.getAntaminenUrl()}/accounts/login"
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

    fun getVehicles(): List<VehicleFacade> {
        val antaminenCarUrl = "${this.getAntaminenUrl()}/accounts/cars"
        val request = Request
                .Builder()
                .url(antaminenCarUrl)
                .addAuthHeader(this)
                .build()

        val requestResult = this.getHttpClient().newCall(request).execute()!!
        val bodyString = requestResult.body()!!.string()!!
        return Gson().fromJson(bodyString)
    }

    fun postVehicle(administrationPostCar: AdministrationPostCar): Boolean {
        val antaminenCarUrl = "${this.getAntaminenUrl()}/accounts/cars"
        println(administrationPostCar.toJson())
        val body = RequestBody.create(this.mediaTypeJson, administrationPostCar.toJson())
        val request = Request
                .Builder()
                .url(antaminenCarUrl)
                .post(body)
                .addAuthHeader(this)
                .build()
        val result = this.getHttpClient().newCall(request).execute()!!
        return result.isSuccessful
    }

    private fun Request.Builder.addAuthHeader(antaminenService: AntaminenService): Request.Builder {
        this.header("Authorization", antaminenService.retrieveJwtToken())
        return this
    }

    companion object {
        @JvmStatic
        fun main(args:Array<String>) {
            val a = AntaminenService()
            a.postVehicle(AdministrationPostCar("2", "05-05-2018", "05-05-2019"))
            val v = a.getVehicles()
            println(v.toJson())
        }
    }
}