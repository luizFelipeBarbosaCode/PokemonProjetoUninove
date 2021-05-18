package com.example.applicationpokemon.core.api

import com.example.applicationpokemon.BuildConfig
import com.example.applicationpokemon.core.utils.Constrants.BASE_URL
import com.example.applicationpokemon.ui.pokemon.data.datasource.IPokemonApiHelper
import com.example.applicationpokemon.ui.pokemon.data.datasource.IPokemonApiService
import com.example.applicationpokemon.ui.pokemon.data.datasource.PokemonApiHelperImpl
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
object NetworkRetrofit {@Singleton
@Provides
fun provideOkHttpClient() = if (BuildConfig.DEBUG) {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
    } else {
        OkHttpClient.Builder()
            .build()
    }
//
    @Singleton
    @Provides
    fun provideRetrofitInstance(client: OkHttpClient) =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()


    @Singleton
    @Provides
    fun providePokemonApiService(retrofitInstance: Retrofit) =
        retrofitInstance.create(IPokemonApiService::class.java)

    @Singleton
    @Provides
    fun providePokemonApiHelper(apiHelperImpl: PokemonApiHelperImpl) : IPokemonApiHelper =
        apiHelperImpl

}