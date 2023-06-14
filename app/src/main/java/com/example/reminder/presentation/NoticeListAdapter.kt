package com.example.reminder.presentation

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.reminder.R
import com.example.reminder.domain.NoticeItem

class NoticeListAdapter: RecyclerView.Adapter<NoticeListAdapter.NoticeListViewHolder>() {

    var count = 0
    var noticeList = listOf<NoticeItem>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoticeListViewHolder {
        Log.d("NoticeListAdapter", "onCreateViewHolder, count: ${++count}")
        val layout = when (viewType) {
            VIEW_TYPE_DISABLED -> R.layout.item_notice_disabled
            VIEW_TYPE_ENABLED -> R.layout.item_notice_enabled
            else -> throw RuntimeException("Unknown view type: $viewType")
        }
        val view = LayoutInflater.from(parent.context).inflate(layout, parent, false)
        return NoticeListViewHolder(view)
    }


    // как вставлять значения внутри этого view
    override fun onBindViewHolder(holder: NoticeListViewHolder, position: Int) {
        val noticeItem = noticeList[position]

        holder.tvName?.text = noticeItem.name
        holder.tvDescription?.text = noticeItem.description
        holder.tvDate?.text = noticeItem.date.toString()
        holder.view.setOnLongClickListener {
            true
        }

    }

    //возвращает количество элементов в коллекцию
    override fun getItemCount(): Int {
        return  noticeList.size
    }

    override fun getItemViewType(position: Int): Int {
        val item = noticeList[position]
        return if (item.enabled) {
            VIEW_TYPE_ENABLED
        } else {
            VIEW_TYPE_DISABLED
        }
    }

    // класс который хранит View
    class NoticeListViewHolder(val view: View): RecyclerView.ViewHolder(view) {
        val tvName = view.findViewById<TextView>(R.id.tv_name)
        val tvDescription = view.findViewById<TextView>(R.id.tv_description)
        val tvDate = view.findViewById<TextView>(R.id.tv_date)
    }

    companion object {

        const val VIEW_TYPE_ENABLED = 100
        const val VIEW_TYPE_DISABLED = 101
        const val MAX_POOL_SIZE = 30
    }
}