package ru.easycode.zerotoheroandroidtdd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private lateinit var textView: TextView
    private lateinit var incrementButton: Button
    private lateinit var decrementButton: Button
    private lateinit var uiState: UiState
    private var count: Count = Count.Base(step = 2, max = 4, min = 0)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textView = findViewById(R.id.countTextView)
        incrementButton = findViewById(R.id.incrementButton)
        decrementButton = findViewById(R.id.decrementButton)
        val textTV = textView.text.toString()
        val init = count.initial(textTV)
        if (init == UiState.Max(textTV)) {
            UiState.Max(textTV).apply(textView, incrementButton, decrementButton)
        } else if (init == UiState.Min(textTV)) {
            UiState.Min(textTV).apply(textView, incrementButton, decrementButton)
        } else {
            UiState.Base(textTV).apply(textView,incrementButton,decrementButton)
        }
        incrementButton.setOnClickListener {
            uiState = count.increment(textTV)
            uiState.apply(textView, incrementButton, decrementButton)
        }
        decrementButton.setOnClickListener {
            uiState = count.decrement(textView.text.toString())
            uiState.apply(textView, incrementButton, decrementButton)
        }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
    }

    companion object {
        private const val KEY = "key"
    }
}
