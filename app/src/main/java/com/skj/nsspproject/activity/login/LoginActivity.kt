package com.skj.nsspproject.activity.login

import android.content.Context
import android.content.Intent
import android.hardware.fingerprint.FingerprintManager
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import com.an.biometric.BiometricCallback
import com.an.biometric.BiometricManager
import com.skj.nsspproject.R
import com.skj.nsspproject.activity.home.MainActivity
import com.skj.nsspproject.base.BaseActivity
import com.skj.nsspproject.databinding.ActivityLoginBinding

class LoginActivity : BaseActivity(), BiometricCallback {

    lateinit var mBinding: ActivityLoginBinding
    var mBiometricManager: BiometricManager? = null

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        mBinding.clickHandler = ClickHandler()

    }

    inner class ClickHandler {

        @RequiresApi(Build.VERSION_CODES.M)
        fun onClickBiometric() {

            mBiometricManager = BiometricManager.BiometricBuilder(this@LoginActivity)
                .setTitle(getString(R.string.app_name))
                .setSubtitle(getString(R.string.title_fingerprint))
                .setDescription(getString(R.string.desc_fingerprint))
                .setNegativeButtonText(getString(R.string.title_activity_home))
                .build()

            //start authentication
            mBiometricManager?.authenticate(this@LoginActivity);
        }

        fun onClickFaceId() {

            Toast.makeText(
                applicationContext,
                "Under development due to some restriction of device specific custom UI",
                Toast.LENGTH_LONG
            ).show()
        }
    }



    override fun onSdkVersionNotSupported() {
        Toast.makeText(
            applicationContext,
            getString(R.string.biometric_error_sdk_not_supported),
            Toast.LENGTH_LONG
        ).show()
    }

    override fun onBiometricAuthenticationNotSupported() {
        Toast.makeText(
            applicationContext,
            getString(R.string.biometric_error_hardware_not_supported),
            Toast.LENGTH_LONG
        ).show()
    }

    override fun onBiometricAuthenticationNotAvailable() {
        Toast.makeText(
            applicationContext,
            getString(R.string.biometric_error_fingerprint_not_available),
            Toast.LENGTH_LONG
        ).show()
    }

    override fun onBiometricAuthenticationPermissionNotGranted() {
        Toast.makeText(
            applicationContext,
            getString(R.string.biometric_error_permission_not_granted),
            Toast.LENGTH_LONG
        ).show()
    }

    override fun onBiometricAuthenticationInternalError(error: String?) {
        Toast.makeText(applicationContext, error, Toast.LENGTH_LONG).show()
    }

    override fun onAuthenticationFailed() {

    }

    override fun onAuthenticationCancelled() {
        Toast.makeText(
            applicationContext,
            getString(R.string.biometric_cancelled),
            Toast.LENGTH_LONG
        ).show()
        mBiometricManager!!.cancelAuthentication()
    }

    override fun onAuthenticationSuccessful() {
        Toast.makeText(
            applicationContext,
            getString(R.string.biometric_success),
            Toast.LENGTH_LONG
        ).show()

        var intent = Intent(this@LoginActivity, MainActivity::class.java)
        startActivity(intent)
        finish()

    }

    override fun onAuthenticationHelp(helpCode: Int, helpString: CharSequence?) {

    }

    override fun onAuthenticationError(errorCode: Int, errString: CharSequence?) {

    }

}