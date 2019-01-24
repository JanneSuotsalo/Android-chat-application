package com.example.janne.chat


import android.content.DialogInterface
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.util.Log
import android.view.*
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var client: ChatClient? = null
    var list = mutableListOf<ChatMessage>()
    var customAdapter: ListAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        client = ChatClient(this, intent.extras["ip"] as String, (intent.extras["port"] as String).toInt())
        Thread(client).start()

        floatingActionButton.setOnClickListener {
            if (!editText.text.toString().isEmpty()) {
                client?.sendMessage(editText.text.toString())
                editText.text.clear()
            }
        }

        val dialog = AlertDialog.Builder(this)
        val dialogView = layoutInflater.inflate(R.layout.custom_dialog, null)
        val username = dialogView.findViewById<EditText>(R.id.editText_username)
        dialog.setView(dialogView)
        dialog.setCancelable(false)
        dialog.setPositiveButton("Send",{ dialogInterface: DialogInterface, i: Int -> })
        val customDialog = dialog.create()
        customDialog.show()

        customDialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener{
            Log.d("TAG6", "????")
            if (!username.text.isEmpty() && username.text != null && username.text.toString() != "console" && username.text.toString() != "Console") {
                client?.sendMessage(":user ${username.text.toString()}")
                customDialog.dismiss()
            } else {
                Toast.makeText(baseContext, "Invalid username", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onStart() {
        super.onStart()
        customAdapter = ListAdapter(this, list)
        listView1.adapter = customAdapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        getMenuInflater().inflate(R.menu.commonmenus,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        var id = item?.getItemId()
        //If connection is not established, buttons does nothing so that the app won't crash
        if(client!!.connected) {
            if (id == R.id.menu_users) {
                Log.d("TAG", "MenuUsers")
                client?.sendMessage(":online")
            }
            if (id == R.id.menu_history) {
                Log.d("TAG", "MenuHistory")
                client?.sendMessage((":history"))
            }
        }

        return super.onOptionsItemSelected(item)
    }

    fun newMessage(message: String){
        runOnUiThread{
            customAdapter?.add(message)
        }
    }
}
