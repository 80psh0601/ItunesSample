package com.example.watcha.application.injection

import android.content.Context
import com.example.watcha.BuildConfig
import com.example.watcha.application.AppConstants.BASE_URL
import com.google.gson.GsonBuilder
import com.example.watcha.remote.api.ApiTrack
import com.example.watcha.remote.db.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    private const val ALL_TIMEOUT = 10L
    @Singleton
    @Provides
    fun provideRetrofitInstance() = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(OkHttpClient().newBuilder().apply {
            BuildConfig.DEBUG.let {
                connectTimeout(ALL_TIMEOUT, TimeUnit.SECONDS)
                writeTimeout(ALL_TIMEOUT, TimeUnit.SECONDS)
                readTimeout(ALL_TIMEOUT, TimeUnit.SECONDS)
            }
        }.build())
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
        .build()

    @Singleton
    @Provides
    fun provideWebService(retrofit:Retrofit) = retrofit.create(ApiTrack::class.java)

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context) = AppDatabase.getDatabase(appContext)

    @Singleton
    @Provides
    fun provideTrackDao(db: AppDatabase) = db.trackDao()
}