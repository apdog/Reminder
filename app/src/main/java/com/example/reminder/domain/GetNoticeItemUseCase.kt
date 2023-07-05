package com.example.reminder.domain
// получаем записку по id
class GetNoticeItemUseCase(private val noticeListRepository: NoticeListRepository) {
    fun getNoticeItemId(noticeItemId: Int): NoticeItem {
        return noticeListRepository.getNoticeItemId(noticeItemId)
    }
}