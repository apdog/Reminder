package com.example.reminder.presentation

import androidx.lifecycle.ViewModel
import com.example.reminder.data.NoticeListRepositoryImpl
import com.example.reminder.domain.AddNoticeItemUseCase
import com.example.reminder.domain.EditNoticeItemUseCase
import com.example.reminder.domain.GetNoticeItemUseCase
import com.example.reminder.domain.NoticeItem
import java.util.Date

class NoticeItemViewModel : ViewModel() {

    private val repository = NoticeListRepositoryImpl

    private val getNoticeItemUseCase = GetNoticeItemUseCase(repository)
    private val addNoticeItemUseCase = AddNoticeItemUseCase(repository)
    private val editNoticeItemUseCase = EditNoticeItemUseCase(repository)

    fun getNoticeItem(noticeItemId: Int){
        val item = getNoticeItemUseCase.getNoticeItemId(noticeItemId)
    }

    fun addNoticeItem(inputName: String?,inputDescription: String?){
        val name = parseName(inputName)
        val description = parseDescription(inputDescription)
        val fieldsValid = validateInput(name, description)
        if (fieldsValid){
            val noticeItem = NoticeItem(name, description, Date(),true)
            addNoticeItemUseCase.addNoticeItem(noticeItem)
        }

    }

    fun editNoticeItem(inputName: String?,inputDescription: String?){
        val name = parseDescription(inputName)
        val description = parseDescription(inputDescription)
        val fieldsValid = validateInput(name, description)
        if (fieldsValid){
            val noticeItem = NoticeItem(name, description, Date(),true)
            editNoticeItemUseCase.editNoticeItem(noticeItem)
        }
    }

    private fun parseName(inputName: String?): String {
        return inputName?.trim() ?: ""
    }

    private fun parseDescription(inputDescription: String?): String {
        return inputDescription?.trim() ?: ""
    }

    private fun validateInput(name: String, description: String): Boolean {
        var result = true
        if (name.isBlank()){
            //TODO: show error input name
            result = false
        }
        if (description.isBlank()){
            //TODO: show error input description
            result = false
        }
        return result
    }
}