package com.udacity

import android.app.Application
import android.os.Build
import com.udacity.utils.createNotificationChannels

class LoadApp: Application() {
    override fun onCreate() {
        super.onCreate()

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            createNotificationChannels()
        }
    }
}