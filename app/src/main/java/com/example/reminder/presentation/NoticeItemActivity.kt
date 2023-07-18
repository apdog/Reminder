package com.example.reminder.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.reminder.R
import com.example.reminder.domain.NoticeItem
import com.google.android.material.textfield.TextInputLayout

class NoticeItemActivity : AppCompatActivity() {

//    private lateinit var viewModel: NoticeItemViewModel
//
//    private lateinit var tilName: TextInputLayout
//    private lateinit var tilDescription: TextInputLayout
//    private lateinit var etName: EditText
//    private lateinit var etDescription: EditText
//    private lateinit var buttonSave: Button
//
//    private var screenMode = MODE_UNKNOWN
//    private var noticeItemId = NoticeItem.UNDEFINED_ID
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notice_item)
//        parseIntent()
//        viewModel = ViewModelProvider(this)[NoticeItemViewModel::class.java]
//        initViews()
//        addTextChangedListeners()
//        launchRightMode()
//        observeViewModel()

    }
//
//    private fun observeViewModel() {
//        viewModel.errorInputDescription.observe(this) {
//            val message = if (it) {
//                getString(R.string.error_input_description)
//            } else {
//                null
//            }
//            tilDescription.error = message
//        }
//        viewModel.errorInputName.observe(this) {
//            val message = if (it) {
//                getString(R.string.error_input_name)
//            } else {
//                null
//            }
//            tilName.error = message
//        }
//        viewModel.shouldCloseScreen.observe(this) {
//            finish()
//        }
//    }
//
//    private fun launchRightMode() {
//        when (screenMode) {
//            MODE_EDIT -> launchEditMode()
//            MODE_ADD -> launchAddMode()
//        }
//    }
//
//    private fun addTextChangedListeners() {
//
//        etName.addTextChangedListener(object : TextWatcher {
//            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
//            }
//
//            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
//                viewModel.resetErrorInputName()
//            }
//
//            override fun afterTextChanged(s: Editable?) {
//            }
//        })
//
//        etDescription.addTextChangedListener(object : TextWatcher {
//            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
//            }
//
//            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
//                viewModel.resetErrorInputDescription()
//            }
//
//            override fun afterTextChanged(s: Editable?) {
//            }
//        })
//    }
//
//    private fun launchEditMode() {
//        viewModel.getNoticeItem(noticeItemId)
//        viewModel.noticeItem.observe(this) {
//            etName.setText(it.name)
//            etDescription.setText(it.description)
//        }
//        buttonSave.setOnClickListener() {
//            viewModel.editNoticeItem(etName.text?.toString(), etDescription.text?.toString())
//        }
//    }
//
//    private fun launchAddMode() {
//        buttonSave.setOnClickListener() {
//            viewModel.addNoticeItem(etName.text?.toString(), etDescription.text?.toString())
//        }
//    }
//
//    private fun parseIntent() {
//        if (!intent.hasExtra(EXTRA_SCREEN_MODE)) {
//            throw RuntimeException("Param screen mode is absent")
//        }
//        val mode = intent.getStringExtra(EXTRA_SCREEN_MODE)
//        if (mode != MODE_EDIT && mode != MODE_ADD) {
//            throw RuntimeException("Unknown screen mode $mode")
//        }
//        screenMode = mode
//        if (screenMode == MODE_EDIT) {
//            if (!intent.hasExtra(EXTRA_NOTICE_ITEM_ID)) {
//                throw RuntimeException("Param notice item id is absent")
//            }
//            noticeItemId = intent.getIntExtra(EXTRA_NOTICE_ITEM_ID, NoticeItem.UNDEFINED_ID)
//        }
//    }
//
//    private fun initViews() {
//        tilName = findViewById(R.id.til_name)
//        tilDescription = findViewById(R.id.til_description)
//        etName = findViewById(R.id.et_name)
//        etDescription = findViewById(R.id.et_desc)
//        buttonSave = findViewById(R.id.save_button)
//    }
//
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
}