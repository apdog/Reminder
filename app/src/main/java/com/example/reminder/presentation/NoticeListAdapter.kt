package com.example.reminder.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.reminder.R
import com.example.reminder.domain.NoticeItem

class NoticeListAdapter : ListAdapter<NoticeItem, NoticeItemViewHolder>(NoticeItemDiffCallback()) {

    // лямбда функция для долгого слушателя ресайкл вью
    // в этой переменной лежит функция, которая принимает NoticeItem и ничего не возвращает,
    // Вэтой перемнной лежит либо функция в скобках, либо null
    var onNoticeItemLongClickListener: ((NoticeItem) -> Unit)? = null
    // лямбда функция для обычного слушателя ресайкл вью
    var onNoticeItemClickListener: ((NoticeItem) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoticeItemViewHolder {
        val layout = when (viewType) {
            VIEW_TYPE_DISABLED -> R.layout.item_notice_disabled
            VIEW_TYPE_ENABLED -> R.layout.item_notice_enabled
            else -> throw RuntimeException("Unknown view type: $viewType")
        }
        val view = LayoutInflater.from(parent.context).inflate(layout, parent, false)
        return NoticeItemViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: NoticeItemViewHolder, position: Int) {
        val noticeItem = getItem(position)
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

    override fun getItemViewType(position: Int): Int {
        val item = getItem(position)
        return if (item.enabled) {
            VIEW_TYPE_ENABLED
        } else {
            VIEW_TYPE_DISABLED
        }
    }

    companion object {
        const val VIEW_TYPE_ENABLED = 100
        const val VIEW_TYPE_DISABLED = 101
        const val MAX_POOL_SIZE = 30
    }
}