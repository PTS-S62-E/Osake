package com.pts62.common.finland.communication.rest

interface IServiceConfiguration {
    fun getServiceAddress(): String
    fun getServiceCredential(): String
    fun getServicePassword(): String
}