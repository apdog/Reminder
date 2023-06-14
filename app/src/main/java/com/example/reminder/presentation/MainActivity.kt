package com.example.reminder.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.reminder.R
import com.example.reminder.domain.NoticeItem
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var noticeListAdapter: NoticeListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupRecyclerView()
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.noticeList.observe(this) {
            noticeListAdapter.noticeList = it
        }
    }

    private fun setupRecyclerView() {
        val rvShopList = findViewById<RecyclerView>(R.id.rv_notice_list)
        with(rvShopList) {
            noticeListAdapter = NoticeListAdapter()
            adapter = noticeListAdapter
            recycledViewPool.setMaxRecycledViews(
                NoticeListAdapter.VIEW_TYPE_ENABLED,
                NoticeListAdapter.MAX_POOL_SIZE
            )
            recycledViewPool.setMaxRecycledViews(
                NoticeListAdapter.VIEW_TYPE_DISABLED,
                NoticeListAdapter.MAX_POOL_SIZE
            )
        }
    }
}
