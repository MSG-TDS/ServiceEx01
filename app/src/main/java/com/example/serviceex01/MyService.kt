package com.example.serviceex01

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log

class MyService : Service() {


    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val action = intent?.action
        Log.d("StartService", "Start Message")
        Log.d("StartService", "action: $action")

        return super.onStartCommand(intent, flags, startId)
    }

    companion object {
        val ACTION_START = "com.example.serviceex01.START"
        val ACTION_STOP = "com.example.serviceex01.STOP"
        val ACTION_RUN = "com.example.serviceex01.RUN"

    }

    override fun onDestroy() {
        Log.d("StopService", "Stop Message")

        super.onDestroy()
    }

    inner class MyBinder: Binder(){
        fun getService():MyService {
            return this@MyService
        }
    }

    val binder = MyBinder()

    override fun onBind(intent: Intent): IBinder {
        return binder
    }

    fun serviceMessage():String{
        return "Hello"
    }
}
