package com.example.janne.chat

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.LinearLayout
import com.example.janne.chat.R.layout.textview
import kotlinx.android.synthetic.main.textview.view.*

class ListAdapter(var context: Context, val list: MutableList<ChatMessage>) : BaseAdapter() {

    override fun getCount(): Int {
        return list.size
    }

    override fun getItem(position: Int): Any {
        return list[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
         var view: LinearLayout

         if (p1 == null) {
             val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
             view = inflater.inflate(textview, null) as LinearLayout
         } else {
             view = p1 as LinearLayout
         }
        view.text_msg.text = list[p0].msg2
        view.text_name.text = list[p0].name
        view.text_date.text = list[p0].date

         return view
     }

    fun add(sh: String){
        list.add(ChatMessage(sh))
        notifyDataSetChanged()
        Log.d("TAG_List", "${list.toString()}")
    }
}