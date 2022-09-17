package com.hackzurich.hackzurich22.base

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val dataStore: DataStore<Preferences>) :
    ViewModel() {

    val shouldShowAssessment = dataStore.data.map { preferences ->
        !(preferences[DataStoreKeys.ASSESSMENT_SHOWN] ?: true)
    }

    fun didShowAssessment() = viewModelScope.launch {
        dataStore.edit { settings ->
            settings[DataStoreKeys.ASSESSMENT_SHOWN] = true
        }
    }
}