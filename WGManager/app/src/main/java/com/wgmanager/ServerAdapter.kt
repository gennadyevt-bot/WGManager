package com.wgmanager

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class ServerAdapter(private val onClick: (Server) -> Unit) :
    ListAdapter<Server, ServerAdapter.ViewHolder>(DiffCallback()) {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val card: CardView = view.findViewById(R.id.serverCard)
        val name: TextView = view.findViewById(R.id.serverName)
        val location: TextView = view.findViewById(R.id.serverLocation)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_server, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val server = getItem(position)
        holder.name.text = server.name
        holder.location.text = server.location
        holder.card.setOnClickListener { onClick(server) }
    }

    class DiffCallback : DiffUtil.ItemCallback<Server>() {
        override fun areItemsTheSame(old: Server, new: Server) = old.id == new.id
        override fun areContentsTheSame(old: Server, new: Server) = old == new
    }
}
