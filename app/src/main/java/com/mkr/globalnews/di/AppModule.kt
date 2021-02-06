package com.mkr.globalnews.di

import com.mkr.globalnews.BuildConfig
import com.mkr.globalnews.repository.NewsRepository
import com.mkr.globalnews.repository.mock.MockNewsRepository
import com.mkr.globalnews.repository.remote.NewsRemoteRepository
import com.mkr.globalnews.repository.remote.NewsRemoteService
import com.mkr.globalnews.viewModel.MainViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(
        baseUrl: String,
        okHttpClient: OkHttpClient,
    ): Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

    @Provides
    fun provideBaseUrl(): String = BuildConfig.BASE_URL

    @Provides
    fun provideNewsService(retrofit: Retrofit): NewsRemoteService =
        retrofit.create(NewsRemoteService::class.java)

    @Provides
    fun provideNewsRepository(service: NewsRemoteService): NewsRepository = if (BuildConfig.SHOULD_USE_MOCK_RESPONSE) {
        MockNewsRepository()
    } else {
        NewsRemoteRepository(service)
    }

    @Provides
    fun provideMainViewModel(repository: NewsRepository): MainViewModel =
        MainViewModel(repository)

    @Provides
    @Singleton
    fun provideOkHttpClient() = if (BuildConfig.DEBUG) {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    } else OkHttpClient
        .Builder()
        .build()

}