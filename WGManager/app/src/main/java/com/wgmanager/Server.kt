package com.wgmanager

data class Server(
    val id: Int,
    val name: String,
    val location: String,
    val countryCode: String,
    val isDefault: Boolean = false
)
