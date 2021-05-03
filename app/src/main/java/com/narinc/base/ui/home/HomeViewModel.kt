package com.narinc.base.ui.home

import androidx.core.util.Pair
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingData
import com.narinc.base.data.model.Item
import com.narinc.base.data.source.AppRepository
import com.narinc.base.util.SingleEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@ExperimentalPagingApi
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: AppRepository
) : ViewModel() {

    private val _showMessageEvent = MutableLiveData<SingleEvent<Int>>()

    var dates: Pair<Long, Long>? = null

    val showMessageEvent: LiveData<SingleEvent<Int>>
        get() = _showMessageEvent

    init {
        val calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"))
        calendar[Calendar.HOUR_OF_DAY] = 0
        calendar[Calendar.MINUTE] = 0
        calendar[Calendar.SECOND] = 0
        calendar[Calendar.MILLISECOND] = 0
        val end = calendar.timeInMillis

        calendar.add(Calendar.DATE, -10)
        val start = calendar.timeInMillis

        dates = Pair(start, end)
    }

    fun loadNews(): Flow<PagingData<Item>> {
        val from: String =
            SimpleDateFormat("yyyy.MM.dd", Locale.getDefault()).format(dates?.first)
        val to: String =
            SimpleDateFormat("yyyy.MM.dd", Locale.getDefault()).format(dates?.second)

        return repository.getRemoteAndLocalFlow(from, to)
    }
}