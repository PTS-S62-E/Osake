package com.pts62.common.finland.communication

interface IQueueSubscribeCallback {

    fun onMessageReceived(message: String)

}