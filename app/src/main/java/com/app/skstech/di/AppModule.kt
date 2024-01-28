package com.app.skstech.di

import android.content.Context
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideApplication(@ApplicationContext context: Context?): MainApp {
        return context as MainApp
    }

    @Provides
    @Singleton
    fun provideFirebaseAuth() =  FirebaseAuth.getInstance()

    @Provides
    @Singleton
    fun getFbDb() =  Firebase.firestore
}