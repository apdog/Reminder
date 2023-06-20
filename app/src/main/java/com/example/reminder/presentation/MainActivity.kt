package com.example.reminder.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.reminder.R

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var noticeListAdapter: NoticeListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupRecyclerView()
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.noticeList.observe(this) {
            noticeListAdapter.submitList(it)
        }
    }

    private fun setupRecyclerView() {
        //настройка rv
        val rvNoticeList = findViewById<RecyclerView>(R.id.rv_notice_list)
        with(rvNoticeList) {
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

        // установим слушатели на адаптер
        setupLongClickListener()
        setupClickListener()

        // создаем колkбек для удаления свайпом влево
        setupSwipeListener(rvNoticeList)

    }

    private fun setupSwipeListener(rvNoticeList: RecyclerView?) {
        val callback = object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val item = noticeListAdapter.currentList[viewHolder.adapterPosition]
                viewModel.deleteNoticeItem(item)
            }

        }

        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(rvNoticeList)
    }

    private fun setupClickListener() {
        noticeListAdapter.onNoticeItemClickListener = {
            Toast.makeText(this, it.name, Toast.LENGTH_SHORT).show()
        }
    }

    private fun setupLongClickListener() {
        noticeListAdapter.onNoticeItemLongClickListener = {
            viewModel.changeEnableState(it)
        }
    }
}
