package com.example.android.rickmorty.di

import android.app.Application
import androidx.room.Room
import com.example.android.rickmorty.data.local.CharacterInfoDatabase
import com.example.android.rickmorty.data.local.Converters
import com.example.android.rickmorty.data.remote.RickAndMortyApi
import com.example.android.rickmorty.data.remote.RickAndMortyApi.Companion.BASE_URL
import com.example.android.rickmorty.data.repository.CharacterRepositoryImpl
import com.example.android.rickmorty.data.util.GsonParser
import com.example.android.rickmorty.domain.repository.CharacterRepository
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRickAndMortyApi(): RickAndMortyApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RickAndMortyApi::class.java)
    }

    @Provides
    @Singleton
    fun provideCharacterInfoDatabase(app: Application): CharacterInfoDatabase {
        return Room.databaseBuilder(
            app,
            CharacterInfoDatabase::class.java,
            "rick_and_morty.db"
        ).addTypeConverter(Converters(GsonParser(Gson())))
            .build()
    }

    @Provides
    @Singleton
    fun provideCharacterRepository(api: RickAndMortyApi, db: CharacterInfoDatabase): CharacterRepository {
        return CharacterRepositoryImpl(api = api, db = db)
    }

}