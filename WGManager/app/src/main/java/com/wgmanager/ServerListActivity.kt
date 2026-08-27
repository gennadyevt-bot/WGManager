package com.wgmanager

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ServerListActivity : AppCompatActivity() {
    private lateinit var adapter: ServerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_server_list)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val addButton = findViewById<ImageButton>(R.id.addServerButton)
        val backButton = findViewById<ImageButton>(R.id.backButton)

        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = ServerAdapter { server ->
            val prefs = getSharedPreferences("wg_prefs", MODE_PRIVATE)
            prefs.edit().putInt("current_server_id", server.id).apply()
            finish()
        }
        recyclerView.adapter = adapter

        addButton.setOnClickListener {
            startActivity(Intent(this, AddServerActivity::class.java))
        }

        backButton.setOnClickListener { finish() }

        loadServers()
    }

    override fun onResume() {
        super.onResume()
        loadServers()
    }

    private fun loadServers() {
        val servers = ServerData.getServers(this)
        adapter.submitList(servers)
    }
}
