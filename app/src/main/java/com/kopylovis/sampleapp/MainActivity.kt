package com.kopylovis.sampleapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.core.content.ContextCompat
import com.kopylovis.sampleapp.databinding.ActivityMainBinding
import com.kopylovis.toastmeister.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setClickListeners()
    }

    private fun setClickListeners() {
        binding.apply {
            amButtonNormalToast.setOnClickListener(this@MainActivity)
            amButtonSuccessToast.setOnClickListener(this@MainActivity)
            amButtonErrorToast.setOnClickListener(this@MainActivity)
            amButtonInfoToast.setOnClickListener(this@MainActivity)
            amButtonCustomToast.setOnClickListener(this@MainActivity)
            amButtonDefaultToast.setOnClickListener(this@MainActivity)
        }
    }

    override fun onClick(v: View?) {
        binding.apply {
            when (v?.id) {
                amButtonNormalToast.id -> this@MainActivity.normalToast(
                    NORMAL_TOAST
                ).show()
                amButtonSuccessToast.id -> this@MainActivity.successToast(
                    R.string.toastmeister_lib_success_toast,
                    ToastMeister.LENGTH_MIDDLE,
                    ToastMeister.GRAVITY_TOP
                ).show()
                amButtonErrorToast.id -> this@MainActivity.errorToast(
                    R.string.toastmeister_lib_error_toast,
                    ToastMeister.LENGTH_LONG,
                    ToastMeister.GRAVITY_TOP
                ).show()
                amButtonInfoToast.id -> this@MainActivity.infoToast(
                    INFO_TOAST,
                    ToastMeister.LENGTH_SHORT,
                    ToastMeister.GRAVITY_BOTTOM
                ).show()
                amButtonCustomToast.id -> this@MainActivity.customToast(
                    CUSTOM_TOAST,
                    ToastMeister.LENGTH_SHORT,
                    ToastMeister.GRAVITY_TOP,
                    150,
                    getColor(R.color.black),
                    40,
                    getColor(R.color.toastmeister_green),
                    18,
                    ContextCompat.getDrawable(this@MainActivity, R.drawable.ic_baseline_soap_24),
                    getColor(R.color.colorPrimary),
                    30,
                    30
                ).show()
                amButtonDefaultToast.id -> this@MainActivity.defaultToast(
                    R.string.toastmeister_lib_default_toast).show()
            }
        }
    }

    companion object {
        const val NORMAL_TOAST = "Normal Toast"
        const val INFO_TOAST = "Info Toast"
        const val CUSTOM_TOAST = "Custom toast"
    }
}
