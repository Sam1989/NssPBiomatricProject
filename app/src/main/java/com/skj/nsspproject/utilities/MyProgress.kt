package com.skj.nsspproject.utilities

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import com.skj.nsspproject.databinding.LayoutProgressBinding



class MyProgress : DialogFragment() {

    lateinit var mBinding: LayoutProgressBinding

    companion object {

        var isShowing = false
        val progressHandler = Handler(Looper.getMainLooper())

        fun show(activity: AppCompatActivity) {
            val pD = activity.supportFragmentManager.findFragmentByTag("_progress_dialog")
            if (pD != null) return
            val dialog = MyProgress()
            dialog.show(activity.supportFragmentManager, "_progress_dialog")
            isShowing = true
        }

        fun hide(activity: AppCompatActivity) {
            val pD = activity.supportFragmentManager.findFragmentByTag("_progress_dialog")
            if (pD != null) {
                try {
                    progressHandler.postDelayed({
                        activity.supportFragmentManager.beginTransaction().remove(pD).commit()
                        isShowing = false
                    }, 100)
                } catch (e: Exception) {
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isCancelable = false
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        super.onCreateDialog(savedInstanceState)
        val builder = AlertDialog.Builder(requireContext())
        val lI =
            requireContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        mBinding = LayoutProgressBinding.inflate(lI, null, false)
        builder.setView(mBinding.root)
        val aD = builder.create()
        aD.window?.setBackgroundDrawableResource(android.R.color.transparent)
        return aD
    }
}