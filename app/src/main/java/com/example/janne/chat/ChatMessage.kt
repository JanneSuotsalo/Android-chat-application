package com.example.janne.chat

import android.util.Log

class ChatMessage(var message :String) {
    val arguments = mutableListOf<List<String>>()
    var name: String = ""
    var msg: String = ""
    var date: String = ""
    var msg2: String = ""
    override fun toString(): String{
        arguments.add(message.split(" ",limit=3))
        name = arguments[0][0]
        msg = arguments[0][2]
        date = arguments[0][1]
        msg2  = msg.replace("|","\n")

        return "$name $date $msg2"
    }
}