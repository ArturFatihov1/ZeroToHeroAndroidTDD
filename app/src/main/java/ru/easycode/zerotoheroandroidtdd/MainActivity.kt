package ru.easycode.zerotoheroandroidtdd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.transition.Visibility

class MainActivity : AppCompatActivity() {
    private lateinit var textView: TextView
    private lateinit var linearLayout: LinearLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val button = findViewById<Button>(R.id.removeButton)
        textView = findViewById(R.id.titleTextView)
        linearLayout = findViewById(R.id.rootLayout)
        button.setOnClickListener {
            linearLayout.removeView(textView)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val removedTextView = linearLayout.childCount == 1
        outState.putBoolean(KEY,removedTextView)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        val removedTextView = savedInstanceState.getBoolean(KEY)
        if(removedTextView)
            linearLayout.removeView(textView)
    }
    companion object {
        private const val KEY = "key"
    }
}