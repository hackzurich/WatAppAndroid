package com.hackzurich.hackzurich22.base

import androidx.datastore.preferences.core.booleanPreferencesKey

object DataStoreKeys {
    val ASSESSMENT_SHOWN = booleanPreferencesKey("assessment_shown")
    val LOCATION_PERMISSION_ASKED = booleanPreferencesKey("location_permission_asked")
}