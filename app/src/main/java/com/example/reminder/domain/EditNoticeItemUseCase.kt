package com.example.reminder.domain
// редактируем элемент из списка
class EditNoticeItemUseCase(private val noticeListRepository: NoticeListRepository) {
    fun editNoticeItem(noticeItem: NoticeItem){
        noticeListRepository.editNoticeItem(noticeItem)
    }
}