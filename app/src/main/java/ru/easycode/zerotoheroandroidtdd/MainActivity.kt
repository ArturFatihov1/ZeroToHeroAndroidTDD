package ru.easycode.zerotoheroandroidtdd

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import java.io.Serializable

class MainActivity : AppCompatActivity() {
    private var state: State = State.Initial
    private lateinit var textView: TextView
    private lateinit var linearLayout: LinearLayout
    private lateinit var button: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button = findViewById(R.id.removeButton)
        textView = findViewById(R.id.titleTextView)
        linearLayout = findViewById(R.id.rootLayout)
        button.setOnClickListener {
            state = State.Removed
            state.apply(linearLayout, textView)
            button.isEnabled = false
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable(KEY,state)
        outState.putBoolean(BUT,button.isEnabled)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        state = if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            savedInstanceState.getSerializable(KEY,State::class.java) as State
        } else {
            savedInstanceState.getSerializable(KEY) as State
        }
        state.apply(linearLayout,textView)
        button.isEnabled = savedInstanceState.getBoolean(BUT)
    }
    companion object {
        private const val KEY = "key"
        private const val BUT = "state"
    }
}
interface State: Serializable {
    fun apply(linearLayout: LinearLayout, textView: TextView)
    object Initial: State {
        override fun apply(linearLayout: LinearLayout, textView: TextView) = Unit
    }
    object Removed: State {
        override fun apply(linearLayout: LinearLayout, textView: TextView) {
            linearLayout.removeView(textView)
        }

    }
}