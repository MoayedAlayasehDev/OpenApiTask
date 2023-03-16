package com.openapi.task.application

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
@HiltAndroidApp
class AppApplication:Application() {
    companion object{
        var instance: AppApplication?=null
        @JvmName("Instance")
        fun getInstance(): AppApplication {
            return instance!!
        }
    }
    override fun onCreate() {
        super.onCreate()
        instance =this
    }
}