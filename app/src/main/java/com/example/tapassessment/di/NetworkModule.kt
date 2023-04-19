package com.example.tapassessment.di

import com.example.tapassessment.network.NetworkService
import com.example.tapassessment.utils.*
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Singleton
    @Provides
    fun provideHttpLogger(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(okHttpLogger: HttpLoggingInterceptor): OkHttpClient {
        val httpClient = OkHttpClient.Builder()
            .connectTimeout(API_CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(API_WRITE_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(API_READ_TIMEOUT, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .addInterceptor{chain ->
                val original = chain.request()
                val url = original.url.newBuilder().addQueryParameter("api_key", API_KEY).build()
                val request = original.newBuilder().url(url).build()
                chain.proceed(request)
            }
            .addInterceptor(okHttpLogger)
        return httpClient.build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

    @Provides
    fun provideNetworkService(retrofit: Retrofit): NetworkService =
        retrofit.create(NetworkService::class.java)
}