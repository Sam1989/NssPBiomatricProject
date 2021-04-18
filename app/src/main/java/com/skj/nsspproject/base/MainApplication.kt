package com.skj.nsspproject.base

import android.app.Application
import android.content.Context


class MainApplication : Application() {

    
    companion object {
        private lateinit var instance: MainApplication
        fun get(): MainApplication = instance

    }

    override fun onCreate() {
        super.onCreate()
        instance = this


        val handler =
            Thread.getDefaultUncaughtExceptionHandler()

        Thread.setDefaultUncaughtExceptionHandler { thread, throwable ->
            handler.uncaughtException(thread, throwable)
        }
    }

    fun getContext(): Context {
        return applicationContext
    }

}