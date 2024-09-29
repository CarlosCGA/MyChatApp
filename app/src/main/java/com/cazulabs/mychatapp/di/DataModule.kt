package com.cazulabs.mychatapp.di

import android.content.Context
import androidx.datastore.core.DataStore
import com.cazulabs.mychatapp.data.database.DatabaseServiceImpl
import com.cazulabs.mychatapp.data.network.FirebaseChatService
import com.cazulabs.mychatapp.domain.DatabaseService
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Singleton
    @Provides
    fun provideDatabaseReference() = Firebase.database.reference

    @Singleton
    @Provides
    fun provideFirebaseService(reference: DatabaseReference) = FirebaseChatService(reference)

    @Singleton
    @Provides
    fun provideDataStoreService(@ApplicationContext context: Context): DatabaseService {
        return DatabaseServiceImpl(context)
    }

}