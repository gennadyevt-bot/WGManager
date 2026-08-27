package com.example.wgmanager

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.animation.doOnEnd
import androidx.core.content.ContextCompat
import com.example.wgmanager.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var isConnected = false
    private var isConnecting = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.vpnButton.setOnClickListener {
            if (isConnecting) return@setOnClickListener
            if (isConnected) {
                disconnect()
            } else {
                connect()
            }
        }
    }

    private fun connect() {
        isConnecting = true
        animateButtonPress()

        // Simulate connecting
        binding.statusText.text = getString(R.string.vpn_status_connecting)
        binding.statusText.setTextColor(ContextCompat.getColor(this, R.color.status_connecting))

        // FIXED: statusDot is View, use setBackgroundColor + GradientDrawable
        val dotDrawable = GradientDrawable()
        dotDrawable.shape = GradientDrawable.OVAL
        dotDrawable.setColor(ContextCompat.getColor(this, R.color.status_connecting))
        binding.statusDot.background = dotDrawable

        // Simulate connection delay
        binding.vpnButtonContainer.postDelayed({
            isConnected = true
            isConnecting = false
            binding.statusText.text = getString(R.string.vpn_status_connected)
            binding.statusText.setTextColor(ContextCompat.getColor(this, R.color.status_connected))

            val connectedDrawable = GradientDrawable()
            connectedDrawable.shape = GradientDrawable.OVAL
            connectedDrawable.setColor(ContextCompat.getColor(this, R.color.status_connected))
            binding.statusDot.background = connectedDrawable

            binding.hintText.text = "Tap to disconnect"
        }, 1500)
    }

    private fun disconnect() {
        isConnected = false
        animateButtonPress()

        binding.statusText.text = getString(R.string.vpn_status_disconnected)
        binding.statusText.setTextColor(ContextCompat.getColor(this, R.color.status_disconnected))

        val disconnectedDrawable = GradientDrawable()
        disconnectedDrawable.shape = GradientDrawable.OVAL
        disconnectedDrawable.setColor(ContextCompat.getColor(this, R.color.status_disconnected))
        binding.statusDot.background = disconnectedDrawable

        binding.hintText.text = getString(R.string.tap_to_connect)
    }

    private fun animateButtonPress() {
        val scaleDownX = ObjectAnimator.ofFloat(binding.vpnButton, "scaleX", 1f, 0.92f)
        val scaleDownY = ObjectAnimator.ofFloat(binding.vpnButton, "scaleY", 1f, 0.92f)
        scaleDownX.duration = 100
        scaleDownY.duration = 100

        val scaleUpX = ObjectAnimator.ofFloat(binding.vpnButton, "scaleX", 0.92f, 1f)
        val scaleUpY = ObjectAnimator.ofFloat(binding.vpnButton, "scaleY", 0.92f, 1f)
        scaleUpX.duration = 150
        scaleUpY.duration = 150

        scaleDownX.start()
        scaleDownY.start()

        scaleDownX.doOnEnd {
            scaleUpX.start()
            scaleUpY.start()
        }
    }
}
