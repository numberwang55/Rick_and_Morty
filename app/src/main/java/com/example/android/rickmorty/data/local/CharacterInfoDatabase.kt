package com.example.android.rickmorty.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.android.rickmorty.data.local.entity.CharacterInfoRemoteKeysEntity
import com.example.android.rickmorty.data.local.entity.ResultInfoEntity

@Database(entities = [ResultInfoEntity::class, CharacterInfoRemoteKeysEntity::class], version = 1)
@TypeConverters(Converters::class)
abstract class CharacterInfoDatabase: RoomDatabase() {

    abstract val resultInfoDao: ResultInfoDao
    abstract val characterInfoRemoteKeysDao: CharacterInfoRemoteKeysDao
}