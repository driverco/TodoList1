package com.driverco.todoList1

import android.app.Activity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_todo.*
import java.io.FileNotFoundException
import java.io.PrintStream
import java.util.*

class TodoActivity : AppCompatActivity() {
    private val todoArray = arrayListOf<String>()
    private lateinit var todoAdapt: TodoAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_todo)
        todoAdapt = TodoAdapter(this, R.layout.todo_task, todoArray)
        todoListView.adapter = todoAdapt
        readTasks()
        addButton.setOnClickListener { addTodo() }
        todoListView.setOnItemLongClickListener { parent: AdapterView<*>, view: View, position: Int, id: Long ->
            //Toast.makeText(this, "position {$position}", Toast.LENGTH_SHORT).show()
            val removed = todoArray[position]
            todoArray.removeAt(position)
            todoAdapt.notifyDataSetChanged()
            writeTasks()
            Toast.makeText(this, "todo: \"$removed\" deleted", Toast.LENGTH_SHORT).show()
            return@setOnItemLongClickListener true
        }

        todoToAdd.setOnKeyListener(object : View.OnKeyListener {
            override fun onKey(v: View?, keyCode: Int, event: KeyEvent): Boolean {
                // If the event is a key-down event on the "enter" button
                if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                    addTodo()
                    return true
                }
                return false
            }
        })
    }

    private fun addTodo() {
        val newTodo = todoToAdd.text.toString()
        if (newTodo.isNotEmpty()) {
            todoAdapt.add(newTodo)
            todoToAdd.setText("")
            writeTasks()
            Toast.makeText(this, "todo: \"$newTodo\" added", Toast.LENGTH_SHORT).show()
        }
    }

    private fun readTasks() {
        try {
            val input = Scanner(openFileInput("tasks.txt"))
            while (input.hasNextLine()) {
                todoAdapt.add(input.nextLine())
            }
            input.close()
        } catch (e: FileNotFoundException) {

        }
    }

    private fun writeTasks() {
        val output = PrintStream(openFileOutput("tasks.txt", Activity.MODE_PRIVATE))
        for (todo in todoArray)
            output.println(todo)
        output.close()

    }
}