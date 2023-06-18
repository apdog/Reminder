package com.example.reminder.presentation

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.reminder.R
import com.example.reminder.domain.NoticeItem

class NoticeListAdapter : RecyclerView.Adapter<NoticeListAdapter.NoticeItemViewHolder>() {

    var count = 0
    var noticeList = listOf<NoticeItem>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    // лямбда функция для долгого слушателя ресайкл вью
    // в этой переменной лежит функция, которая принимает NoticeItem и ничего не возвращает,
    // Вэтой перемнной лежит либо функция в скобках, либо null
    var onNoticeItemLongClickListener: ((NoticeItem) -> Unit)? = null

    // лямбда функция для обычного слушателя ресайкл вью
    var onNoticeItemClickListener: ((NoticeItem) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoticeItemViewHolder {
        Log.d("ShopListAdapter", "onCreateViewHolder, count: ${++count}")
        val layout = when (viewType) {
            VIEW_TYPE_DISABLED -> R.layout.item_notice_disabled
            VIEW_TYPE_ENABLED -> R.layout.item_notice_enabled
            else -> throw RuntimeException("Unknown view type: $viewType")
        }
        val view = LayoutInflater.from(parent.context).inflate(layout, parent, false)
        return NoticeItemViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: NoticeItemViewHolder, position: Int) {
        val noticeItem = noticeList[position]
        viewHolder.view.setOnLongClickListener {
            onNoticeItemLongClickListener?.invoke(noticeItem)
            true
        }
        viewHolder.view.setOnClickListener {
            onNoticeItemClickListener?.invoke(noticeItem)
            true
        }
        viewHolder.tvName?.text = noticeItem.name
        viewHolder.tvDescription?.text = noticeItem.description
        viewHolder.tvDate?.text = noticeItem.date.toString()
    }

    override fun onViewRecycled(viewHolder: NoticeItemViewHolder) {
        super.onViewRecycled(viewHolder)
        viewHolder.tvName?.text = ""
        viewHolder.tvDescription?.text = ""
        viewHolder.tvDate?.text = ""
        viewHolder.tvName?.setTextColor(
            ContextCompat.getColor(
                viewHolder.view.context,
                android.R.color.white
            )
        )
    }

    override fun getItemCount(): Int {
        return noticeList.size
    }

    override fun getItemViewType(position: Int): Int {
        val item = noticeList[position]
        return if (item.enabled) {
            VIEW_TYPE_ENABLED
        } else {
            VIEW_TYPE_DISABLED
        }
    }

    class NoticeItemViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
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