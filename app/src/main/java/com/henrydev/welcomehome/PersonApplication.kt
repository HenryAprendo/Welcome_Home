package com.henrydev.welcomehome

import android.app.Application
import com.henrydev.welcomehome.data.AppContainer
import com.henrydev.welcomehome.data.DefaultAppContainer

class PersonApplication: Application() {

    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer(this)
    }

}