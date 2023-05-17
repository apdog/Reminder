package com.example.reminder.domain
// добавляем элемент в список
class AddNoticeItemUseCase(private val noticeListRepository: NoticeListRepository) {
//реализация UseCase
    fun addNoticeItem(noticeItem: NoticeItem) {
        noticeListRepository.addNoticeItem(noticeItem)
    }
}