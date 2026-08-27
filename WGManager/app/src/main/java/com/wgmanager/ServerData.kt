package com.wgmanager

import android.content.Context
import org.json.JSONArray
import org.json.JSONObject

object ServerData {
    private const val PREFS_NAME = "wg_servers"
    private const val KEY_SERVERS = "servers_list"

    fun getServers(context: Context): List<Server> {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val json = prefs.getString(KEY_SERVERS, null)
        val list = mutableListOf<Server>()

        if (json != null) {
            val arr = JSONArray(json)
            for (i in 0 until arr.length()) {
                val obj = arr.getJSONObject(i)
                list.add(Server(
                    obj.getInt("id"),
                    obj.getString("name"),
                    obj.getString("location"),
                    obj.getString("countryCode"),
                    obj.optBoolean("isDefault", false)
                ))
            }
        } else {
            list.add(Server(0, "DE-FRA-01", "Germany, Frankfurt", "de", true))
            list.add(Server(1, "US-NYC-01", "USA, New York", "us", false))
            list.add(Server(2, "NL-AMS-01", "Netherlands, Amsterdam", "nl", false))
            saveServers(context, list)
        }
        return list
    }

    fun addServer(context: Context, server: Server) {
        val list = getServers(context).toMutableList()
        val newId = (list.maxOfOrNull { it.id } ?: -1) + 1
        list.add(server.copy(id = newId))
        saveServers(context, list)
    }

    fun deleteServer(context: Context, id: Int) {
        val list = getServers(context).toMutableList()
        list.removeAll { it.id == id }
        saveServers(context, list)
    }

    private fun saveServers(context: Context, list: List<Server>) {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val arr = JSONArray()
        list.forEach { s ->
            val obj = JSONObject()
            obj.put("id", s.id)
            obj.put("name", s.name)
            obj.put("location", s.location)
            obj.put("countryCode", s.countryCode)
            obj.put("isDefault", s.isDefault)
            arr.put(obj)
        }
        prefs.edit().putString(KEY_SERVERS, arr.toString()).apply()
    }
}
