package com.example.reminder.domain

import androidx.lifecycle.LiveData

// репозиторий должен уметь делать все, что требуется Use Cases.
// Домэйн слой работает с интерфейсом репозитория и домэйн слой не должен знать как происходит
// обработка данных, он не должен зависить от конкретных ситочников, поэтому все юз кейсы зависят от
// репозитория, а не от конкретной реализации
interface NoticeListRepository {

    fun addNoticeItem(noticeItem: NoticeItem)

    fun deleteNoticeItem(noticeItem: NoticeItem)

    fun editNoticeItem(noticeItem: NoticeItem)

    fun getNoticeItemId(noticeItemId: Int): NoticeItem

    fun getNoticeList(): LiveData<List<NoticeItem>>
}