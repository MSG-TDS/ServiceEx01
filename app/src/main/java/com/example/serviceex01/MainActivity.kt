package com.example.serviceex01

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.view.View
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    fun serviceStart(view: View){
        val intent = Intent(this, MyService::class.java)
        intent.action = MyService.ACTION_START

        startService(intent)
    }

    fun serviceStop(view: View){
        val intent = Intent(this, MyService::class.java)
        intent.action = MyService.ACTION_STOP

        stopService(intent)
    }

    var myService:MyService? = null
    var isService = false
    val connection = object : ServiceConnection{
        override fun onServiceDisconnected(name: ComponentName?) {
            isService = false
        }

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            val binder = service as MyService.MyBinder
            myService = binder.getService()
            isService = true
        }
    }

    fun serviceBind(view:View){
        val intent = Intent(this, MyService::class.java)
        bindService(intent, connection, Context.BIND_AUTO_CREATE)
    }

    fun serviceUnbound(view:View) {
        if(isService){
            unbindService(connection)
            isService = false
        }
    }

    fun callServiceFunction(view:View){
        if(isService){
            val message = myService?.serviceMessage()
            Toast.makeText(this, "Message: $message", Toast.LENGTH_SHORT).show()
        }
        else{
            Toast.makeText(this, "Message: Service Not Started", Toast.LENGTH_SHORT).show()
        }
    }
}