package com.example.android.rickmorty.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.android.rickandmorty.data.local.entity.CharacterInfoRemoteKeysEntity

@Dao
interface CharacterInfoRemoteKeysDao {

    @Query("SELECT * FROM characterinforemotekeysentity WHERE id =:id")
    suspend fun getRemoteKeys(id: Int): CharacterInfoRemoteKeysEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllRemoteKeys(remoteKeys: List<CharacterInfoRemoteKeysEntity>)

    @Query("DELETE FROM characterinforemotekeysentity")
    suspend fun deleteAllRemoteKeys()
}