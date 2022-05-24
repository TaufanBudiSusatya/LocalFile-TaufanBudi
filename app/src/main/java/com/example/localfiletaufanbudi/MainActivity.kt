package com.example.localfiletaufanbudi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.DocumentsContract
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    val CREATE_FILE = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Mendeklrasikan dari btnSimpan
        val btnSimpan = findViewById<Button>(R.id.btnSimpan)

        //merekam perintah saat btnsimpan di klik
        btnSimpan.setOnClickListener {
            //jika di klik maka akan pindah ke directory download pada handphone
            val intent = Intent(Intent.ACTION_CREATE_DOCUMENT).apply {
                addCategory(Intent.CATEGORY_OPENABLE)
                type = "text/plain"
                putExtra(Intent.EXTRA_TITLE, "filename.txt")
                putExtra(DocumentsContract.EXTRA_INITIAL_URI, "")
            }
            startActivityForResult(intent, CREATE_FILE)
        }
    }

    //menghasilkan dari hasil aktivitas
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        //membuat file baru dari file yang sudah kita buat
        if (resultCode ==CREATE_FILE && resultCode == RESULT_OK){
            val uri = data!!.data
            try {
                val outputStream= this.contentResolver.openOutputStream(uri!!)
                outputStream?.write("CodeLib File Save Demo".toByteArray())
                outputStream?.close()
                Toast.makeText(this,"File Berhasil Disimpan",Toast.LENGTH_LONG).show()

            } catch (e:Exception){
                Toast.makeText(this,"File Gagal Disimpan",Toast.LENGTH_LONG).show()

            }
        }
    }
}