package com.example.reminder.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.reminder.data.NoticeListRepositoryImpl
import com.example.reminder.domain.DeleteNoticeItemUseCase
import com.example.reminder.domain.EditeNoticeItemUseCase
import com.example.reminder.domain.GetNoticeListUseCase
import com.example.reminder.domain.NoticeItem

// Взаимодействием с Домэйн слоем занимается ViewModel
// в этом классе описываем функции бизнес-логики необходимые на главном экране (реализуем логику на экране)
// т.е этот класс содержит поля для всех отображаемых на экране данных и методы для обработки всех действий пользователя
// взаимодействие активити и view model должно проиходиьь серез LiveData
class MainViewModel : ViewModel() {

    private val repository = NoticeListRepositoryImpl
    // методы бизнес логики вызываются из UseCase (Interactors)
    private val getNoticeListUseCase = GetNoticeListUseCase(repository)
    private val deleteNoticeItemUseCase = DeleteNoticeItemUseCase(repository)
    private val editNoticeItemUseCase = EditeNoticeItemUseCase(repository)

    val noticeList = getNoticeListUseCase.getNoticeList()

    //для удаления элемента из списка
    fun deleteNoticeItem(noticeItem: NoticeItem) {
        deleteNoticeItemUseCase.deleteNoticeItem(noticeItem)
    }

    //создаем копию объекта, с состоянием enabled false для отображения в другом виде
    fun changeEnabledState(noticeItem: NoticeItem) {
        val newItem = noticeItem.copy(enabled = !noticeItem.enabled)
        editNoticeItemUseCase.editNoticeItem(noticeItem)
    }
}