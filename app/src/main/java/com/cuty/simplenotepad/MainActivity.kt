package com.cuty.simplenotepad

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private val newWordsActivityRequestCode = 1

    companion object{
        val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
         var currentDate :String = sdf.format(Date())
        lateinit var activityViewModel: ActivityViewModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = RecyclerViewAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)




        activityViewModel = ViewModelProvider(this).get(ActivityViewModel::class.java)
        activityViewModel.allWords.observe(this, Observer { words ->
            //Update the cached copy of the words in the adapter
            words?.let { adapter.setWords(it) }

        })
        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            val intent = Intent(this, AllWordsActivity::class.java)
            startActivityForResult(intent, newWordsActivityRequestCode)

        }









    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == newWordsActivityRequestCode && resultCode == Activity.RESULT_OK){
            data?.getStringExtra(AllWordsActivity.EXTRA_REPLY)?.let {
                currentDate = sdf.format(Date())
                val nota = Word(it, currentDate )
                activityViewModel.insert(nota)
                Unit

            }
        }else{
            Toast.makeText(applicationContext,"empty", Toast.LENGTH_LONG).show()
        }
    }


}
