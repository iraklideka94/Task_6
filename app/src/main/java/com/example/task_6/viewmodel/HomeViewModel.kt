package com.example.task_6.viewmodel

import androidx.lifecycle.ViewModel
import com.example.task_6.DataStoreHandler


class HomeViewModel : ViewModel() {

    fun getPreferences() = DataStoreHandler.getPreferences()

    suspend fun clear() {
        DataStoreHandler.clear()
    }

    // With Remove
    suspend fun remove(key: String) {
        DataStoreHandler.remove(key)
    }


}