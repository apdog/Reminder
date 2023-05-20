package com.example.reminder.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.reminder.R
import com.example.reminder.domain.NoticeItem

class NoticeListAdapter: RecyclerView.Adapter<NoticeListAdapter.NoticeListViewHolder>() {

    val list = listOf<NoticeItem>()

    // метод определяет как пересоздавать в view
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoticeListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_notice_enabled, parent, false)
        return NoticeListViewHolder(view)
    }

    // как вставлять значения внутри этого view
    override fun onBindViewHolder(holder: NoticeListViewHolder, position: Int) {
        val noticeItem = list[position]

        holder.tvName?.text = noticeItem.name
        holder.tvDescription?.text = noticeItem.description
        holder.tvDate?.text = noticeItem.date.toString()
        holder.view.setOnLongClickListener {
            true
        }

    }

    //возвращает количество элементов в коллекцию
    override fun getItemCount(): Int {
        return  list.size
    }

    // класс который хранит View
    class NoticeListViewHolder(val view: View): RecyclerView.ViewHolder(view) {
        val tvName = view.findViewById<TextView>(R.id.tv_name)
        val tvDescription = view.findViewById<TextView>(R.id.tv_description)
        val tvDate = view.findViewById<TextView>(R.id.tv_date)
    }
}