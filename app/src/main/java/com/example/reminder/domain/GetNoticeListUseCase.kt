package com.example.reminder.domain

import androidx.lifecycle.LiveData

// Создаем класс отвечающий за 1 операцию. еще это называется интерактором
class GetNoticeListUseCase(private val noticeListRepository: NoticeListRepository) {
// метод не принимает параметров и возвращает Список из записок
    fun getNoticeList(): LiveData<List<NoticeItem>> {
        return noticeListRepository.getNoticeList()
    }
}