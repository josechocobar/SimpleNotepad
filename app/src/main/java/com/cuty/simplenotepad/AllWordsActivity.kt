package com.cuty.simplenotepad

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.EditText
import kotlinx.android.synthetic.main.activity_all_words.*

class AllWordsActivity : AppCompatActivity() {
    private lateinit var tvPalabra: EditText
    companion object
    {
        const val EXTRA_REPLY = "com.example.room"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_words)
        tvPalabra = findViewById(R.id.tvPalabra)
        buEstabien.setOnClickListener {

            val replyIntent = Intent()
            if (TextUtils.isEmpty(tvPalabra.text)) {
                setResult(Activity.RESULT_CANCELED, replyIntent)
            } else {
                val word = tvPalabra.text.toString()
                replyIntent.putExtra(EXTRA_REPLY, word)
                setResult(Activity.RESULT_OK, replyIntent)
            }
            finish()
        }
    }
}
