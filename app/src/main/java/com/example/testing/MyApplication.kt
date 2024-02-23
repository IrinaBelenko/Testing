package com.example.testing

import android.app.Application

class MyApplication : Application() {
    lateinit var repo: Repository
    override fun onCreate() {
        super.onCreate()
        instance = this
        repo = Repository(ApiClient())
    }
    companion object {
        lateinit var instance: MyApplication
            private set
    }
}
