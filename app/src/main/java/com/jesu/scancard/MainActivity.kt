package com.jesu.scancard

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val REQUEST_IMAGE_CAPTURE = 1
    private var boolImg = true
    private val REQUEST_CODE_CAMERA = 101
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        ivFrontView.setOnClickListener {
            /**
             * boolImg =true for front view
             */
            if (checkPermission()) {
                boolImg = true
                disptachTakePictureIntent()
            }


        }
        ivBackView.setOnClickListener {

            /**
             * boolImg false for back view
             */
            if (checkPermission()) {
                boolImg = false
                disptachTakePictureIntent()
            }

        }
    }

    private fun requestsPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)) {

        } else {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), REQUEST_CODE_CAMERA)

        }
    }


    fun disptachTakePictureIntent() {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            takePictureIntent.resolveActivity(packageManager)
                ?.also { startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE) }

        }
    }


   private fun checkPermission() =
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            requestsPermission()
            false
        } else true


    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when (requestCode) {
            REQUEST_CODE_CAMERA -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Permission granted", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(this, "Permission denied", Toast.LENGTH_LONG).show()
                }
                return
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {
            val bitmapParams = data?.extras?.get("data") as Bitmap
            if (boolImg)
                ivFrontView.setImageBitmap(bitmapParams)
            else
                ivBackView.setImageBitmap(bitmapParams)
        }
    }

}
