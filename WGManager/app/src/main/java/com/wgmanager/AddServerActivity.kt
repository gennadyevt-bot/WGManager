package com.wgmanager

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class AddServerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_server)

        val backButton = findViewById<ImageButton>(R.id.backButton)
        val nameInput = findViewById<EditText>(R.id.serverNameInput)
        val locationInput = findViewById<EditText>(R.id.serverLocationInput)
        val codeInput = findViewById<EditText>(R.id.countryCodeInput)
        val saveButton = findViewById<Button>(R.id.saveButton)

        backButton.setOnClickListener { finish() }

        saveButton.setOnClickListener {
            val name = nameInput.text.toString().trim()
            val location = locationInput.text.toString().trim()
            val code = codeInput.text.toString().trim()

            if (name.isEmpty() || location.isEmpty()) {
                Toast.makeText(this, "Fill all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            ServerData.addServer(this, Server(
                id = 0,
                name = name,
                location = location,
                countryCode = code.ifEmpty { "xx" }
            ))

            Toast.makeText(this, "Server added", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}
