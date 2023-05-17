package com.example.reminder.domain
//удаляем элемент из списка
class DeleteNoticeItemUseCase(private val noticeListRepository: NoticeListRepository) {
    fun deleteNoticeItem(noticeItem: NoticeItem){
        noticeListRepository.deleteNoticeItem(noticeItem)
    }
}