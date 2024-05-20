package com.example.application


import android.app.Application
import com.example.application.model.AppContainer
import com.example.application.model.AppDataContainer

class UserApplication: Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}