package com.example.procattemplate

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        super.findViewById<Button>(R.id.catalogButton).setOnClickListener {
            val myToast = Toast.makeText(this, "Hello Toast!", Toast.LENGTH_SHORT)
            myToast.show()
        }

        super.findViewById<com.google.android.material.floatingactionbutton.FloatingActionButton>(R.id.rightMenuButton).setOnClickListener {
            setContentView(R.layout.right_menu)
        }

        super.findViewById<Button>(R.id.backButton).setOnClickListener {
            setContentView(R.layout.activity_main)
        }

    }


}