package com.example.reminder.presentation

import androidx.lifecycle.ViewModel
import com.example.reminder.data.NoticeListRepositoryImpl
import com.example.reminder.domain.DeleteNoticeItemUseCase
import com.example.reminder.domain.EditNoticeItemUseCase
import com.example.reminder.domain.GetNoticeListUseCase
import com.example.reminder.domain.NoticeItem

// Взаимодействием с Домэйн слоем занимается ViewModel
// в этом классе описываем функции бизнес-логики необходимые на главном экране (реализуем логику на экране)
// т.е этот класс содержит поля для всех отображаемых на экране данных и методы для обработки всех действий пользователя
// взаимодействие активити и view model должно проиходиьь серез LiveData
class MainViewModel : ViewModel() {

    private val repository = NoticeListRepositoryImpl

    private val getNoticeListUseCase = GetNoticeListUseCase(repository)
    private val deleteNoticeItemUseCase = DeleteNoticeItemUseCase(repository)
    private val editNoticeItemUseCase = EditNoticeItemUseCase(repository)

    val noticeList = getNoticeListUseCase.getNoticeList()

    fun deleteNoticeItem(shopItem: NoticeItem) {
        deleteNoticeItemUseCase.deleteNoticeItem(shopItem)
    }

    fun changeEnableState(shopItem: NoticeItem) {
        val newItem = shopItem.copy(enabled = !shopItem.enabled)
        editNoticeItemUseCase.editNoticeItem(newItem)
    }
}