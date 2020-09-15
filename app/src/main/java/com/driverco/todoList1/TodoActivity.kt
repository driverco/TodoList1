package com.driverco.todoList1

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
        readTodos()
        addButton.setOnClickListener { _ -> addTodo() }
    }

    private fun addTodo() {
        val newTodo = todoToAdd.text.toString();
        if (newTodo.isNotEmpty()) {
            todoAdapt.add(newTodo);
            todoToAdd.setText("");
            writeTodos()
        }
    }

    private fun readTodos() {
        try{
            val input = Scanner(openFileInput("todos.txt"))
            while (input.hasNextLine()) {
                todoAdapt.add(input.nextLine())
            }
            input.close()
        }catch (e:FileNotFoundException){

        }
    }

    private fun writeTodos() {
        val output = PrintStream(openFileOutput("todos.txt", Activity.MODE_PRIVATE))
        for (todo in todoArray)
            output.println(todo)
        output.close()

    }
}