package com.wgmanager

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    private lateinit var prefs: SharedPreferences
    private var isConnected = false
    private var currentServer: Server? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        prefs = getSharedPreferences("wg_prefs", MODE_PRIVATE)

        val statusText = findViewById<TextView>(R.id.statusText)
        val serverName = findViewById<TextView>(R.id.serverName)
        val serverLocation = findViewById<TextView>(R.id.serverLocation)
        val connectButton = findViewById<CardView>(R.id.connectButton)
        val vpnText = findViewById<TextView>(R.id.vpnText)
        val statusDot = findViewById<View>(R.id.statusDot)
        val serverCard = findViewById<CardView>(R.id.serverCard)
        val addServerButton = findViewById<ImageButton>(R.id.addServerButton)

        loadCurrentServer()

        serverName.text = currentServer?.name ?: "DE-FRA-01"
        serverLocation.text = currentServer?.location ?: "Germany, Frankfurt"

        updateUI(statusText, vpnText, statusDot)

        connectButton.setOnClickListener {
            isConnected = !isConnected
            prefs.edit().putBoolean("connected", isConnected).apply()
            updateUI(statusText, vpnText, statusDot)
        }

        serverCard.setOnClickListener {
            startActivity(Intent(this, ServerListActivity::class.java))
        }

        addServerButton.setOnClickListener {
            startActivity(Intent(this, AddServerActivity::class.java))
        }
    }

    override fun onResume() {
        super.onResume()
        loadCurrentServer()
        findViewById<TextView>(R.id.serverName).text = currentServer?.name ?: "DE-FRA-01"
        findViewById<TextView>(R.id.serverLocation).text = currentServer?.location ?: "Germany, Frankfurt"
    }

    private fun loadCurrentServer() {
        val serverId = prefs.getInt("current_server_id", -1)
        if (serverId != -1) {
            val servers = ServerData.getServers(this)
            currentServer = servers.find { it.id == serverId }
        }
        if (currentServer == null) {
            currentServer = Server(0, "DE-FRA-01", "Germany, Frankfurt", "de", true)
        }
        isConnected = prefs.getBoolean("connected", false)
    }

    private fun updateUI(statusText: TextView, vpnText: TextView, statusDot: View) {
        if (isConnected) {
            statusText.text = "Connected"
            statusText.setTextColor(ContextCompat.getColor(this, R.color.green))
            vpnText.text = "Tap to disconnect"
            statusDot.setBackgroundResource(R.drawable.dot_green)
        } else {
            statusText.text = "Disconnected"
            statusText.setTextColor(ContextCompat.getColor(this, R.color.gray))
            vpnText.text = "Tap to connect"
            statusDot.setBackgroundResource(R.drawable.dot_gray)
        }
    }
}
