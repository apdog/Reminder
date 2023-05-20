package com.example.reminder.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.lifecycle.ViewModelProvider
import com.example.reminder.R
import com.example.reminder.domain.NoticeItem
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    private lateinit var llNoticeList: LinearLayoutCompat

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        llNoticeList = findViewById(R.id.ll_notice_list)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.noticeList.observe(this) {
            showList(it)
        }
    }

    private fun showList(list: List<NoticeItem>) {

        llNoticeList.removeAllViews()
        for(noticeItem in list) {
            val layoutId = if (noticeItem.enabled) {
                R.layout.item_notice_enabled
            } else {
                R.layout.item_notice_disabled
            }
            val view = LayoutInflater.from(this).inflate(layoutId, llNoticeList, false)
            llNoticeList.addView(view)
        }
    }
}