package com.skj.nsspproject.base

import android.content.DialogInterface


interface AsyncViewController {

    fun showProgressDialog()

    fun hideProgressDialog()

    fun showAlertDialog(msg: String, btnListener: DialogInterface.OnClickListener?)

    fun hideAlertDialog()

    fun hideKeyboard()

    fun showKeyboard()

    fun onNoInternet()

}