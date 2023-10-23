package ru.hse.todolists.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.hse.todolists.data.entities.SimpleList

class DashboardViewModel : ViewModel() {

    private val hardcodedLists = listOf(
        SimpleList("first list", "created", "item 1, item 2, item 3"),
        SimpleList("second list", "planned", "item 1, item 2, item 3"),
        SimpleList("third list", "in progress", "item 1, item 2, item 3"),
        SimpleList("fourth list", "waiting", "item 1, item 2, item 3")
    )

    private val _lists = MutableLiveData<List<SimpleList>>(hardcodedLists)
    val lists: LiveData<List<SimpleList>> = _lists

}
