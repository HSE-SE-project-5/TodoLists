package ru.hse.todolists.ui.dashboard

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.work.Worker
import androidx.work.WorkerParameters
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONException
import org.json.JSONObject
import ru.hse.todolists.data.entities.SimpleList
import ru.hse.todolists.data.remote.ListsDatabase
import java.io.IOException

class DashboardViewModel : ViewModel() {

    private val hardcodedLists = listOf(
        SimpleList("first list", "created", "item 1, item 2, item 3"),
        SimpleList("second list", "planned", "item 1, item 2, item 3"),
        SimpleList("third list", "in progress", "item 1, item 2, item 3"),
        SimpleList("fourth list", "waiting", "item 1, item 2, item 3")
    )

    private val listsDatabase = ListsDatabase()

    private val _lists = MutableLiveData<List<SimpleList>>(hardcodedLists)
    val lists: LiveData<List<SimpleList>> = _lists

    private val serviceJob = Job()
    private val serviceScope = CoroutineScope(Dispatchers.Main + serviceJob)

    fun loadAllLists() {
        serviceScope.launch {
            val lists = listsDatabase.getAllLists()
            _lists.postValue(lists)
        }
    }
}
