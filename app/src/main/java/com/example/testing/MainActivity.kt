package com.example.testing

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val label = findViewById<TextView>(R.id.helloTextView)
        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener {
           GlobalScope.launch(Dispatchers.Main) {label.text = "pros"
               delay(2000)
               label.text = R.string.hello_user.toString()}

        }
    }

}

class TestableClass {
    fun getGreeting() = "Hello World"
}
