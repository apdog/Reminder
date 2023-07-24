package com.example.reminder.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.reminder.R
import com.example.reminder.domain.NoticeItem

class NoticeItemActivity : AppCompatActivity(), NoticeItemFragment.OnEditingFinishedListener {

    private var screenMode = MODE_UNKNOWN
    private var noticeItemId = NoticeItem.UNDEFINED_ID
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("NoticeItemFragment", "onCreate")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notice_item)
        parseIntent()
        if (savedInstanceState == null) {
            launchRightMode()
        }
    }

    private fun launchRightMode() {
        val fragment = when (screenMode) {
            MODE_EDIT -> NoticeItemFragment.newInstanceEditItem(noticeItemId)
            MODE_ADD -> NoticeItemFragment.newInstanceAddItem()
            else -> throw RuntimeException("Unknown screen mode $screenMode")
        }
        supportFragmentManager.beginTransaction()
            .replace(R.id.notice_item_container, fragment)
            .commit()
    }

    private fun parseIntent() {
        if (!intent.hasExtra(EXTRA_SCREEN_MODE)) {
            throw RuntimeException("Param screen mode is absent")
        }
        val mode = intent.getStringExtra(EXTRA_SCREEN_MODE)
        if (mode != MODE_EDIT && mode != MODE_ADD) {
            throw RuntimeException("Unknown screen mode $mode")
        }
        screenMode = mode
        if (screenMode == MODE_EDIT) {
            if (!intent.hasExtra(EXTRA_NOTICE_ITEM_ID)) {
                throw RuntimeException("Param notice item id is absent")
            }
            noticeItemId = intent.getIntExtra(EXTRA_NOTICE_ITEM_ID, NoticeItem.UNDEFINED_ID)
        }
    }

    companion object {
        private const val EXTRA_SCREEN_MODE = "extra_mode"
        private const val EXTRA_NOTICE_ITEM_ID = "extra_notice_item_id"
        private const val MODE_EDIT = "mode_edit"
        private const val MODE_ADD = "mode_add"
        private const val MODE_UNKNOWN = ""

        fun newIntentAddItem(context: Context): Intent {
            val intent = Intent(context, NoticeItemActivity::class.java)
            intent.putExtra(EXTRA_SCREEN_MODE, MODE_ADD)
            return intent
        }

        fun newIntentEditItem(context: Context, noticeItemId: Int): Intent {
            val intent = Intent(context, NoticeItemActivity::class.java)
            intent.putExtra(EXTRA_SCREEN_MODE, MODE_EDIT)
            intent.putExtra(EXTRA_NOTICE_ITEM_ID, noticeItemId)
            return intent
        }
    }

    override fun onEditingFinished() {
        finish()
    }
}