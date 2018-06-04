package com.pts62.common.finland.communication.rest

import okhttp3.MediaType
import okhttp3.OkHttpClient

abstract class RestService {

    protected val mediaTypeJson = MediaType.parse("application/json; charset=utf-8")

    protected var customConfig: IServiceConfiguration? = null

    val httpClient by lazy {
        OkHttpClient
                .Builder()
                .build()!!
    }

    abstract fun getDefaultConfig():IServiceConfiguration

    fun getConfig(): IServiceConfiguration {
        if (this.customConfig != null) {
            return this.customConfig!!
        }
        return this.getDefaultConfig()
    }

    fun overrideConfig(config: IServiceConfiguration) {
        this.customConfig = config
    }

}