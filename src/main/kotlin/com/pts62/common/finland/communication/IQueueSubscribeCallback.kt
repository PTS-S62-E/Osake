package com.pts62.common.finland.communication

interface IQueueSubscribeCallback<T> {

    fun onMessageReceived(message: T)

}