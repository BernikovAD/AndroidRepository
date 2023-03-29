package com.example.androidrepository.di.module

import com.example.androidrepository.BuildConfig
import com.example.androidrepository.domain.IRetrofitAPI
import com.example.androidrepository.domain.repository.RepositoryDomain
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {
    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun providesOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder().writeTimeout(1, TimeUnit.MINUTES)
            .readTimeout(1, TimeUnit.MINUTES)
            .callTimeout(1, TimeUnit.MINUTES)
            .addInterceptor(httpLoggingInterceptor)
     /*       .addInterceptor { chain ->
                val newRequest = chain.request().newBuilder()
                    .addHeader("apikey", BuildConfig.apiKey)
                    .build()
                chain.proceed(newRequest)
            }*/
            .build()
    }

    @Provides
    @Singleton
    fun providesHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return if (BuildConfig.DEBUG)
            HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
        else
            HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.NONE
            }
    }

    @Provides
    @Singleton
    fun providesService(retrofit: Retrofit): IRetrofitAPI {
        return retrofit.create(IRetrofitAPI::class.java)
    }

    @Provides
    @Singleton
    fun providesRepositoryDomain(service: IRetrofitAPI): RepositoryDomain {
        return RepositoryDomain(service)
    }

}