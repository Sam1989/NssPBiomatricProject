package com.skj.nsspproject.webservice;


import com.skj.nsspproject.model.PictureResponse



interface ApiResponseListener {

    fun onApiCallSuccess(apiUrl: String, body:PictureResponse): Boolean

    fun onApiCallFailed(apiUrl: String, status: String, errorMessage: String): Boolean
}