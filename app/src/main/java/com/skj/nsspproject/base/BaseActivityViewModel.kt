package com.skj.nsspproject.base

import androidx.lifecycle.MutableLiveData



class BaseActivityViewModel(viewController: AsyncViewController) : BaseViewModel(viewController) {

    val progressDialogStatus: MutableLiveData<String> = MutableLiveData()
    val alertDialogController: MutableLiveData<String> = MutableLiveData()
    val keyboardController: MutableLiveData<Boolean> = MutableLiveData()
    var alertDialogSpecs: AlertDialogSpecs = AlertDialogSpecs()

}