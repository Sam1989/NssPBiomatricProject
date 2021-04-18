package com.skj.nsspproject.base

import android.view.View
import androidx.appcompat.widget.Toolbar

interface CommonCallbacks : AsyncViewController {


    fun hideKeyboard(v: View)


    fun isConnectedToNetwork(): Boolean

    fun getSharedModel(): BaseActivityViewModel

}