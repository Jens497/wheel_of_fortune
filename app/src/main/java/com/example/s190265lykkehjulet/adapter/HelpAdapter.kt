package com.example.s190265lykkehjulet.adapter

import android.content.Context
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.s190265lykkehjulet.R
import com.example.s190265lykkehjulet.model.DataSource

class HelpAdapter() : RecyclerView.Adapter<HelpAdapter.HelpViewHolder>() {

    private val helpText : List<String> = DataSource.rules

    class HelpViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val textView = view.findViewById<TextView>(R.id.help_text)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HelpAdapter.HelpViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.help_item_view, parent, false)
        layout.accessibilityDelegate = View.AccessibilityDelegate()

        return HelpViewHolder(layout)
    }

    override fun onBindViewHolder(holder: HelpAdapter.HelpViewHolder, position: Int) {
        val item = helpText[position]
        holder.textView.text = item
    }

    override fun getItemCount(): Int {
        return helpText.size
    }
}