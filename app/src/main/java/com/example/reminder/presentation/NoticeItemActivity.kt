package com.example.reminder.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.reminder.R

class NoticeItemActivity : AppCompatActivity() {

    private lateinit var  viewModel: NoticeItemViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notice_item)

    }
}