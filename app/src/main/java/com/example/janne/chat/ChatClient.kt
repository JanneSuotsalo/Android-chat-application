package com.example.janne.chat

import android.app.Activity
import android.util.Log
import com.example.janne.chat.R.id.editText
import java.io.BufferedWriter
import java.io.InputStream
import java.io.OutputStream
import java.io.PrintStream
import java.net.Socket
import java.util.*

class ChatClient(val mainActivity :MainActivity, var ip: String,  var portNumber: Int) :Socket(),Runnable{

    lateinit var out: PrintStream
    private var input :Scanner? = null
    var connected: Boolean = false

    override fun run() {
        Log.d("TAG", "1")
        try {
            val socket = Socket(ip , portNumber)
             out = PrintStream(socket.getOutputStream(),true)
             input = Scanner(socket.getInputStream())
            //Checks that the socket is connected
            if(socket.isConnected){
                connected = true
            }

            while (true) {
                val message = input?.nextLine()
                Log.d("TAG", message)
                mainActivity.newMessage(message as String)
            }
        } catch (e: Exception){
            println("Got exception:" + e.message)
        } finally{
            println("Done")
            mainActivity.finish()
        }
    }

    fun sendMessage(message :String){
        Log.d("TAG", message)
        Thread{out.println(message)}.start()
    }
}