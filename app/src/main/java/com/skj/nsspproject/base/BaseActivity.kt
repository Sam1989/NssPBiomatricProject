package com.skj.nsspproject.base

import android.content.Context
import android.content.DialogInterface
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.skj.nsspproject.R
import com.skj.nsspproject.utilities.GeneralCallback


abstract class BaseActivity : AppCompatActivity(), CommonCallbacks, GeneralCallback,
    DialogInterface.OnClickListener {

    private val mBaseViewModel by viewModels<BaseActivityViewModel> { MyViewModelProvider(this as AsyncViewController) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setSoftInputMode(
            WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN
                    or WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN
        )
    }

    override fun showProgressDialog() {
        if (mBaseViewModel.progressDialogStatus.value == null || !mBaseViewModel.progressDialogStatus.value.equals("_show")) {
            runOnUiThread {
                mBaseViewModel.progressDialogStatus.value = "_show"
            }
        }
    }

    override fun hideProgressDialog() {
        if (mBaseViewModel.progressDialogStatus.value == null || !mBaseViewModel.progressDialogStatus.value.equals(
                "_hide"
            )
        ) {
            runOnUiThread {
                mBaseViewModel.progressDialogStatus.value = "_hide"
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun getSharedModel(): BaseActivityViewModel {
        return mBaseViewModel
    }

    override fun showAlertDialog(msg: String, btnListener: DialogInterface.OnClickListener?) {
        runOnUiThread {
            mBaseViewModel.alertDialogSpecs = AlertDialogSpecs()
            mBaseViewModel.alertDialogSpecs.alertDialogBtnListener = btnListener
            mBaseViewModel.alertDialogController.value = msg
        }
    }

    override fun hideAlertDialog() {
        runOnUiThread {
            mBaseViewModel.alertDialogController.value = "null"
        }
    }

    override fun hideKeyboard() {
        runOnUiThread {
            mBaseViewModel.keyboardController.value = false
        }
    }

    override fun hideKeyboard(v: View) {
        runOnUiThread {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(v.windowToken, 0)
        }
    }

    override fun showKeyboard() {
        runOnUiThread {
            mBaseViewModel.keyboardController.value = true
        }
    }

    override fun onNoInternet() {
        runOnUiThread {
            showAlertDialog(getString(R.string.no_network_connected), null)
        }
    }

    override fun onClick(dialog: DialogInterface?, which: Int) {
        when (which) {
            DialogInterface.BUTTON_POSITIVE -> {
            }
            DialogInterface.BUTTON_NEGATIVE -> {
            }
        }
        dialog!!.dismiss()
    }

    override fun isConnectedToNetwork(): Boolean {
        val cm =
            MainApplication.get().getContext()
                .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val ni = cm.activeNetworkInfo
        return ni != null
    }

}