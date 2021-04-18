package com.skj.nsspproject.webservice;

import com.skj.nsspproject.model.PictureResponse

import com.google.gson.reflect.TypeToken

object ApiRegister {

    private const val API_LIVE_URL = "https://pixabay.com/api/"
    const val BASE_URL = API_LIVE_URL

    const val fetchPicture = "?key=21201214-b3c864ce3ec62a8ddc5065a33&="

    fun getApiRequestType(url: String): ApiRequestType {

        val result = ApiRequestType()

        when (url) {

            fetchPicture -> {
                result.responseType =
                    object : TypeToken<PictureResponse>() {}.type
                result.url = BASE_URL + fetchPicture
                result.requestType = RequestType.GET
                return result
            }
        }
        throw IllegalStateException("API is not registered")
    }
}