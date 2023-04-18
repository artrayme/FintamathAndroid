package com.fintamath.history

import java.time.LocalDateTime

object HistoryStorage {

    var onItemRemoved: ((Int) -> Unit)? = null
    var onItemInserted: ((Int) -> Unit)? = null

    private const val maxItemsNum = 50

    private var historyList = arrayListOf<HistoryItem>()

    init {
        // TODO: read data from file
    }

    fun getList(): MutableList<HistoryItem> {
        return historyList
    }

    fun save(text: String) {
        if (!historyList.any { it.text == text }) {
            if (historyList.size == maxItemsNum) {
                historyList.removeLast()
                onItemRemoved?.invoke(maxItemsNum)
            }

            historyList.add(0, HistoryItem(text, false, LocalDateTime.now()))
            onItemRemoved?.invoke(0)
        }

        // TODO: write data to file
    }

    fun remove(index: Int) {
        historyList.removeAt(index)
        onItemRemoved?.invoke(index)
    }

    fun setIsBookmarked(index: Int, isBookmarked: Boolean) {
        if (historyList[index].isBookmarked != isBookmarked) {
            historyList[index].isBookmarked = isBookmarked
        }
    }
}
