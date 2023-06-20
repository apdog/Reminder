package com.example.reminder.presentation

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.reminder.R

class NoticeItemViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
    val tvName = view.findViewById<TextView>(R.id.tv_name)
    val tvDescription = view.findViewById<TextView>(R.id.tv_description)
    val tvDate = view.findViewById<TextView>(R.id.tv_date)
}