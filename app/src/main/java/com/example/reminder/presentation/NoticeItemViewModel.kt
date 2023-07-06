package com.example.reminder.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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

    private val _errorInputName = MutableLiveData<Boolean>()
    val errorInputName: LiveData<Boolean>
        get() = _errorInputName

    private val _errorInputDescription = MutableLiveData<Boolean>()
    val errorInputDescription: LiveData<Boolean>
        get() = _errorInputDescription

    private val _noticeItem = MutableLiveData<NoticeItem>()
    val noticeItem: LiveData<NoticeItem>
        get() = _noticeItem

    private val _shouldCloseScreen = MutableLiveData<Unit>()
    val shouldCloseScreen: LiveData<Unit>
        get() = _shouldCloseScreen

    fun getNoticeItem(noticeItemId: Int){
        val item = getNoticeItemUseCase.getNoticeItemId(noticeItemId)
        _noticeItem.value = item
    }

    fun addNoticeItem(inputName: String?,inputDescription: String?){
        val name = parseName(inputName)
        val description = parseDescription(inputDescription)
        val fieldsValid = validateInput(name, description)
        if (fieldsValid){
            val noticeItem = NoticeItem(name, description, Date(),true)
            addNoticeItemUseCase.addNoticeItem(noticeItem)
            finishWork()
        }

    }

    fun editNoticeItem(inputName: String?,inputDescription: String?){
        val name = parseDescription(inputName)
        val description = parseDescription(inputDescription)
        val fieldsValid = validateInput(name, description)
        if (fieldsValid){
            _noticeItem.value?.let {
                val item = it.copy(name = name, description = description)
                editNoticeItemUseCase.editNoticeItem(item)
                finishWork()
            }
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
            _errorInputName.value = true
            result = false
        }
        if (description.isBlank()){
            _errorInputDescription.value = true
            result = false
        }
        return result
    }

    fun resetErrorInputName() {
        _errorInputName.value = false
    }

    fun resetErrorInputDescription() {
        _errorInputDescription.value = false
    }
    private fun finishWork() {
        _shouldCloseScreen.value = Unit
    }
}