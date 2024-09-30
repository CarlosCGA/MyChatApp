package com.cazulabs.mychatapp.data.database

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.cazulabs.mychatapp.domain.DatabaseService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DatabaseServiceImpl @Inject constructor(private val context: Context): DatabaseService {

    companion object {
        private val USER_NAME = stringPreferencesKey("username")
    }

    private val Context.userPreferencesDataStore: DataStore<Preferences> by preferencesDataStore(
        name = "user"
    )

    override suspend fun saveUsername(username: String) {
        context.userPreferencesDataStore.edit {preferences ->
            preferences[USER_NAME] = username
        }
    }

    override suspend fun getUsername(): Flow<String> {
        return context.userPreferencesDataStore.data.map { preferences -> preferences[USER_NAME] ?: "" }
    }

    override suspend fun logOut() {
        context.userPreferencesDataStore.edit {preferences ->
            preferences[USER_NAME] = ""
        }
    }
}