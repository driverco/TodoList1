package com.driverco.todoList1

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class TodoAdapter(context: Context, val layout: Int, val data: ArrayList<String>) :
    ArrayAdapter<String>(context, layout, data) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val todo = data[position]
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(layout, parent, false);
        val todoText = view.findViewById<TextView>(R.id.textViewTodo)
        todoText.text = todo

        return view
    }

}

