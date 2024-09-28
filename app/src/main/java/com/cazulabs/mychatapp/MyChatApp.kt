package com.cazulabs.mychatapp

import android.app.Application
import com.google.firebase.FirebaseApp

class MyChatApp: Application() {

    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
    }

}