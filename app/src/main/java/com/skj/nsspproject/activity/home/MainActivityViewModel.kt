package com.skj.nsspproject.activity.home

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import com.skj.nsspproject.base.AsyncViewController
import com.skj.nsspproject.base.BaseViewModel
import com.skj.nsspproject.model.PictureRequest
import com.skj.nsspproject.model.PictureResponse
import com.skj.nsspproject.webservice.ApiRegister

class MainActivityViewModel(controller: AsyncViewController) : BaseViewModel(controller) {

    val requestPicture = ObservableField<PictureRequest>()
    val responsePicture = MutableLiveData<PictureResponse>()

    init {
        requestPicture.set(PictureRequest())
    }


    fun callGetNearByRestaurantsListApi() {

        baseRepo.restClient.callPclApi(
            ApiRegister.fetchPicture,
            requestPicture.get()!!,
            responsePicture,
            true
        )
    }
}