package com.example.examq

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Button
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity



class MenuDepan : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cardview_depan)

        startLockTask()
        val button_exit: Button = findViewById(R.id.buttonExit)
        val button1: Button = findViewById(R.id.button1)
        val button2: Button = findViewById(R.id.button2)
        val button3: Button = findViewById(R.id.button3)
        val button4: Button = findViewById(R.id.button4)

        button_exit.setOnClickListener{
            stopLockTask()
            moveTaskToBack(true)
            finishAffinity()
        }

        button1.setOnClickListener {
            val newWindow = Intent(this@MenuDepan, HalSoal::class.java)
            newWindow.putExtra("linkQR", "https://link.smpn5sinjai.sch.id/SoalKelas7")
            startActivity(newWindow)
        }

        button2.setOnClickListener {
            val newWindow = Intent(this@MenuDepan, HalSoal::class.java)
            newWindow.putExtra("linkQR", "https://link.smpn5sinjai.sch.id/SoalKelas8")
            startActivity(newWindow)
        }

        button3.setOnClickListener {
            val newWindow = Intent(this@MenuDepan, HalSoal::class.java)
            newWindow.putExtra("linkQR", "https://link.smpn5sinjai.sch.id/SoalKelas9")
            startActivity(newWindow)
        }

        button4.setOnClickListener {
            val newWindow = Intent(this@MenuDepan, MainActivity::class.java)
            startActivity(newWindow)
        }
    }
    override fun onBackPressed() {
        // Tidak melakukan apa-apa saat tombol kembali ditekan
   }

    override fun onStop() {
        super.onStop()
        // Memeriksa apakah aplikasi masih dalam mode kunci tugas saat aplikasi berhenti
        if (isTaskRoot) {
            val intent = Intent(Intent.ACTION_MAIN)
            intent.addCategory(Intent.CATEGORY_HOME)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }
    }
}