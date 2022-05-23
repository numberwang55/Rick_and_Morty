package com.example.android.rickmorty.data.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.android.rickmorty.data.local.entity.ResultInfoEntity

@Dao
interface ResultInfoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacterResultInfo(resultInfo: List<ResultInfoEntity>)

    @Query("DELETE FROM resultinfoentity")
    suspend fun clearResultInfo()

    @Query("SELECT * FROM resultinfoentity WHERE id LIKE '%' || :id || '%'")
    suspend fun getResultInfoById(id: Int): ResultInfoEntity

    @Query("SELECT * FROM resultinfoentity")
    fun getAllResultInfo(): PagingSource<Int, ResultInfoEntity>

}