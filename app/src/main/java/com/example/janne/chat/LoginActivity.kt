package com.example.janne.chat

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        button_send.setOnClickListener(){
            connectServer()
        }
    }

    fun connectServer(){
        var ipAddress = editText_Ip.text.toString()
        var portAddress = editText_port.text.toString()
        val intent = Intent(this, MainActivity::class.java)
        if(ipAddress!= null && !ipAddress.isEmpty() && portAddress != null) {
            intent.putExtra("ip", ipAddress)
            intent.putExtra("port", portAddress)
            startActivity(intent)
        }
    }
}
