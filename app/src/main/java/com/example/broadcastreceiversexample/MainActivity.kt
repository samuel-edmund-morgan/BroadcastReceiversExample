package com.example.broadcastreceiversexample

import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.broadcastreceiversexample.databinding.ActivityMainBinding

//Broadcast receivers are used for responding to a system-wide events
class MainActivity : AppCompatActivity() {
    private val activityMainBinding by lazy{ ActivityMainBinding.inflate(layoutInflater)}
    lateinit var  receiver: AirplaneModeChangeReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activityMainBinding.root)

        receiver = AirplaneModeChangeReceiver()

        //We need to define intent filter, it determines which apps want to receive which intents
        IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED).also{
            registerReceiver(receiver, it)
        }
    }

    //Prevent memory leak, always unregister receivers
    override fun onStop() {
        super.onStop()
        unregisterReceiver(receiver)
    }
}


//There are two different types of receivers: static and dynamic
// static rec  -  declared in manifect and work after app is closed
// but after API level 26 - there are only dynamic receivers left