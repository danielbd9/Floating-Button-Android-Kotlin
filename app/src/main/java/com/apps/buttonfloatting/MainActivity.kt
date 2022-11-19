package com.apps.buttonfloatting

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.apps.buttonfloatting.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity() {
    private var dX = 0F
    private var dY = 0F
    private var startTime = 0L
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        setupView()
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun setupView() {
        binding.ivFloat.setOnTouchListener { view, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    dX = view.x - event.rawX
                    dY = view.y -event.rawY
                    startTime = Calendar.getInstance ().timeInMillis
                }
                MotionEvent.ACTION_MOVE -> {
                    view.y = event.rawY + dY
                    view.x = event . rawX +dX
                }
                MotionEvent.ACTION_UP -> {
                    val clickDuration =
                        Calendar.getInstance().timeInMillis - startTime

                    if (clickDuration < MAX_CLICK_DURATION)
                        Toast.makeText(this, getString(R.string.app_click), Toast.LENGTH_SHORT).show()
                }
            }
            return@setOnTouchListener true
        }
    }

    companion object {
        private var MAX_CLICK_DURATION = 200
    }
}