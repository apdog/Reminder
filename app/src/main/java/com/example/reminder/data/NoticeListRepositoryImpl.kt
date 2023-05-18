package com.example.reminder.data

import com.example.reminder.domain.NoticeItem
import com.example.reminder.domain.NoticeListRepository
// Data слой отвечает за работу с данными, он предоставляет конкретную реализацию репозитория
// Data слой зависит от domain-слоя и знает о нем
// Дата слой не знает ни чего о Дата слое и от него не зависет
// классы которые являются реализацией только 1го интерфейса пишутся с суффиксом Impl
// Object является синглтоном, т.е. где бы мы не обратились к данному объекту, это будет один и тот же экземпляр
object NoticeListRepositoryImpl : NoticeListRepository {

    private val noticeList = mutableListOf<NoticeItem>()

    private var autoIncrementId = 0

    override fun addNoticeItem(noticeItem: NoticeItem) {
        if(noticeItem.id == NoticeItem.UNDEFINED_ID) {
            noticeItem.id = autoIncrementId++
        }
        noticeList.add(noticeItem)
    }

    override fun deleteNoticeItem(noticeItem: NoticeItem) {
        noticeList.remove(noticeItem)
    }

    override fun editNoticeItem(noticeItem: NoticeItem) {
        // находим старый элемент по id, удаляем, добавляем новый
        val oldElement = getNoticeItemId(noticeItem.id)
        noticeList.remove(oldElement)
        addNoticeItem(noticeItem)
    }

    override fun getNoticeItemId(noticeItemId: Int): NoticeItem {
        return noticeList.find {
            it.id == noticeItemId
        } ?: throw RuntimeException("Element with id $noticeItemId not found")
    }

    override fun getNoticeList(): List<NoticeItem> {
        return noticeList.toMutableList()
    }
}