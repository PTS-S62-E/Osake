package com.pts62.common.finland.communication

import com.pts62.common.facade.CountryFacade

class CommunicationBuilder {

    private var topicRouterKey = Array(3, { "*" })

    /**
     * Creates the topic id based on message, application and country
     * Subscribes to EVERYTHING when nothing is set
     */
    fun build() = this.topicRouterKey.joinToString(".")

    /**
     * Each application specifies this themself
     */
    fun setMessage(msg:String) {
        this.topicRouterKey[2] = msg
    }

    /**
     * Sets the application the message should be sent to
     */
    fun setApplication(application: String) {
        this.topicRouterKey[1] = application
    }

    /**
     * Sets the country that should receive this message, based on countrycode
     * @see CountryFacade.countryCode
     */
    fun setCountry(country: String) {
        this.topicRouterKey[0] = country
    }

    /**
     * Sets the country that should receive this message, based on countrycode
     * @see CountryFacade.countryCode
     */
    fun setCountry(countryFacade: CountryFacade) = this.setCountry(countryFacade.countryCode)

}