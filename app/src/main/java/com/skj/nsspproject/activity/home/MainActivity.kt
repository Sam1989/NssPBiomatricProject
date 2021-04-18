package com.skj.nsspproject.activity.home

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.skj.nsspproject.R
import com.skj.nsspproject.activity.PictureAdaptor

import com.skj.nsspproject.base.AsyncViewController
import com.skj.nsspproject.base.BaseActivity
import com.skj.nsspproject.base.MyViewModelProvider
import com.skj.nsspproject.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {

    lateinit var mBinding: ActivityMainBinding

    private lateinit var nearByRestaurantsAdaptor: PictureAdaptor

    private val mViewModel by viewModels<MainActivityViewModel> {
        MyViewModelProvider(
            this as AsyncViewController
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        mBinding.clickHandler = ClickHandler()

        setupRecycler()

        setupObserver()

    }

    private fun setupObserver() {
        mViewModel.responsePicture.observe(this, Observer {
            if (it != null) {
                try {
                    mBinding.progressBar.visibility= View.GONE
                    nearByRestaurantsAdaptor.setNewData(ArrayList(it.hits))
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        })
    }

    private fun setupRecycler() {
        nearByRestaurantsAdaptor = PictureAdaptor(ArrayList())
        mBinding.recyclerview.adapter = nearByRestaurantsAdaptor
    }

    inner class ClickHandler {
        fun onClickSearch() {
            if (mBinding.editSearch.text.isNotEmpty()) {
                mBinding.progressBar.visibility= View.VISIBLE
                mViewModel.requestPicture.get()!!.type = mBinding.editSearch.text.toString().trim()
                mViewModel.callGetNearByRestaurantsListApi()
            } else {
                Toast.makeText(
                    applicationContext,
                    "Please enter keyword",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

}