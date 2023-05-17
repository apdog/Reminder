package com.example.reminder.domain

import java.util.Date

// Cоздаем Data class - основа бизнес логики
data class NoticeItem(
    val id: Int,
    val name: String,
    val description: String,
    val date: Date,
    val enabled: Boolean
)