package com.example.reminder.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.reminder.domain.NoticeItem
import com.example.reminder.domain.NoticeListRepository
import java.util.Date
import kotlin.random.Random

// Data слой отвечает за работу с данными, он предоставляет конкретную реализацию репозитория
// Data слой зависит от domain-слоя и знает о нем
// Дата слой не знает ни чего о Дата слое и от него не зависет
// классы которые являются реализацией только 1го интерфейса пишутся с суффиксом Impl
// Object является синглтоном, т.е. где бы мы не обратились к данному объекту, это будет один и тот же экземпляр
object NoticeListRepositoryImpl : NoticeListRepository {

    private val noticeListLD = MutableLiveData<List<NoticeItem>>()

    private val noticeList = sortedSetOf<NoticeItem>({ o1, o2 -> o1.id.compareTo(o2.id) })

    private var autoIncrementId = 0
    //создаем через блок init имровизированную БД
    init {
        for (i in 0 until 10) {
            val item = NoticeItem("Name $i", "description", date = Date(), Random.nextBoolean(), i)
            addNoticeItem(item)
        }
    }

    override fun addNoticeItem(noticeItem: NoticeItem) {
        if(noticeItem.id == NoticeItem.UNDEFINED_ID) {
            noticeItem.id = autoIncrementId++
        }
        noticeList.add(noticeItem)
        updateList()
    }

    override fun deleteNoticeItem(noticeItem: NoticeItem) {
        noticeList.remove(noticeItem)
        updateList()
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

    override fun getNoticeList(): LiveData<List<NoticeItem>> {
        return noticeListLD
    }

    // метод для обновления LiveData
    private fun updateList() {
        noticeListLD.value = noticeList.toMutableList()
    }
}