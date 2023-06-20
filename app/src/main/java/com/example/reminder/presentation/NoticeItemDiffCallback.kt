package com.example.reminder.presentation

import androidx.recyclerview.widget.DiffUtil
import com.example.reminder.domain.NoticeItem

class NoticeItemDiffCallback : DiffUtil.ItemCallback<NoticeItem>() {

    override fun areItemsTheSame(oldItem: NoticeItem, newItem: NoticeItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: NoticeItem, newItem: NoticeItem): Boolean {
        return oldItem == newItem
    }
}