package ru.easycode.zerotoheroandroidtdd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.transition.Visibility

class MainActivity : AppCompatActivity() {
    private lateinit var title: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btn = findViewById<Button>(R.id.hideButton)
        title = findViewById<TextView>(R.id.titleTextView)
        btn.setOnClickListener {
            title.visibility = View.INVISIBLE
         }
    }
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(KEY,title.visibility)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        title.visibility = savedInstanceState.getInt(KEY)
    }
    companion object {
        private const val KEY = "visibilityKey"
    }
}