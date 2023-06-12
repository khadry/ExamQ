package com.example.examq

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.InputType
import android.view.WindowManager
import android.webkit.CookieManager
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity


class HalSoal : AppCompatActivity() {
    private lateinit var webSoal: WebView
    private lateinit var btnRefresh: Button
//    private lateinit var progressBar: ProgressBar

    private operator fun String.invoke(b: Boolean): Any? = Unit

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_SECURE,
            WindowManager.LayoutParams.FLAG_SECURE
        )


        setContentView(R.layout.activity_hal_soal)

        val linkQR = intent.getStringExtra("linkQR")

        webSoal = findViewById(R.id.soalFulSkrin)
//        progressBar = findViewById(R.id.progressBar)
        btnRefresh = findViewById(R.id.reload_button)

        webSoal.webViewClient = WebViewClient()
        webSoal.webChromeClient = WebChromeClient()
        webSoal.clearCache(true)
        webSoal.settings.javaScriptEnabled = true
        webSoal.settings.domStorageEnabled = true

        val cookieManager = CookieManager.getInstance()
        cookieManager.removeAllCookies(null) // Menghapus semua cookie saat aplikasi dimulai

        object : WebViewClient() {
            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                clearCacheAndCookies()
            }
        }
        if (linkQR != null) {
            webSoal.loadUrl(linkQR)
        }

        btnRefresh.setOnClickListener {
            webSoal.reload()
        }

    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun clearCacheAndCookies() {
        webSoal.clearCache(true) // Membersihkan cache saat halaman web selesai dimuat

        val cookieManager = CookieManager.getInstance()
        cookieManager.removeAllCookies(null) // Menghapus semua cookie saat halaman web selesai dimuat
    }

    //    inner class MyWebViewClient : WebViewClient() {
//        override fun onPageFinished(view: WebView?, url: String?) {
//            super.onPageFinished(view, url)
//            progressBar.visibility = ProgressBar.GONE
//        }
//    }
//
//    inner class MyWebChromeClient : WebChromeClient() {
//        override fun onProgressChanged(view: WebView?, newProgress: Int) {
//            super.onProgressChanged(view, newProgress)
//            progressBar.progress = newProgress
//        }
//    }
    override fun onBackPressed() {
        val alertdialog: android.app.AlertDialog.Builder = android.app.AlertDialog.Builder(this)
        alertdialog.setTitle("Peringatan")
        alertdialog.setMessage("Apakah anda telah selesai mengerjakan soal, dan ingin keluar ???")
        alertdialog.setPositiveButton(
            "Iya"
        ) { _, _ ->
            val intent = Intent(this@HalSoal, MenuDepan::class.java)
            startActivity(intent)
            this@HalSoal.finish()
        }
        alertdialog.setNegativeButton(
            "Tidak"
        ) { dialog, _ -> dialog.cancel() }
        alertdialog.create()
        alertdialog.show()
    }

    override fun onPause() {
        super.onPause()
        var passwordAttempts = 0
        fun showPasswordDialog() {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Melapor ke pengawas Ruang")
            builder.setMessage("Jangan keluar dari aplikasi saat sedang mengerjakan soal!!!")

            val input = EditText(this)
            input.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
            builder.setView(input)

            builder.setPositiveButton("OK") { dialog, _ ->
                val enteredPassword = input.text.toString()
                val desiredPassword = "40304519" // Ganti dengan password yang diinginkan

                if (enteredPassword == desiredPassword) {
                    // Password benar, lakukan tindakan yang diinginkan
                    // TODO: Tambahkan tindakan yang diinginkan di sini
                    dialog.dismiss()
                } else {
                    passwordAttempts++
                    if (passwordAttempts < 5) {
                        Toast.makeText(this, "Password Salah", Toast.LENGTH_SHORT).show()
                        showPasswordDialog() // Memanggil ulang dialog jika password salah
                    } else {
                        Toast.makeText(
                            this,
                            "Anda telah gagal memasukkan password 5 kali. Aplikasi akan ditutup.",
                            Toast.LENGTH_LONG
                        ).show()
                        finish() // Menutup aplikasi setelah tiga kali gagal memasukkan password
                    }
                }
            }

            val dialog = builder.create()
            dialog.setCancelable(false) // Tidak bisa dibatalkan (cancelable)
            dialog.setCanceledOnTouchOutside(false) // Tidak bisa dibatalkan dengan klik di luar dialog
            dialog.show()
        }

// Panggil metode showPasswordDialog() untuk menampilkan dialog password
        showPasswordDialog()
    }
/*

    override fun onStop() {
        super.onStop()
    }

    //Fires after the OnStop() state
    override fun onDestroy() {
        super.onDestroy()
        try {
            trimCache(this)
        } catch (e: Exception) {
            // TODO Auto-generated catch block
            e.printStackTrace()
        }
    }

    fun trimCache(context: Context) {
        try {
            val dir = context.cacheDir
            if (dir != null && dir.isDirectory) {
                deleteDir(dir)
            }
        } catch (e: Exception) {
            // TODO: handle exception
        }
    }

    fun deleteDir(dir: File?): Boolean {
        if (dir != null && dir.isDirectory) {
            val children = dir.list()
            for (i in children.indices) {
                val success = deleteDir(File(dir, children[i]))
                if (!success) {
                    return false
                }
            }
        }

        // The directory is now empty so delete it
        return dir!!.delete()
    }
*/

}



