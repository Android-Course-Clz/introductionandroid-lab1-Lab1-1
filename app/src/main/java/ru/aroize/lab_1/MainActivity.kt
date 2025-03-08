package ru.aroize.lab_1

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(){

    private lateinit var greetingsView: TextView

    override fun onCreate(savedInstanceState :  Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        greetingsView = findViewById(R.id.greetings)

        greetingsView.setOnClickListener{
            Log.i(LOG_TAG, "Buttom was clicked!")
            greetingsView.text = getString(R.string.colleague_greetings)
        }
    }

    private companion object {
        const val LOG_TAG = "IntroLoggingTag"
    }
}