package com.example.android.rickmorty.data.local

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.example.android.rickmorty.data.util.JsonParser
import com.example.android.rickmorty.domain.model.ResultInfo
import com.google.gson.reflect.TypeToken

@ProvidedTypeConverter
class Converters(
    private val jsonParser: JsonParser
) {

    @TypeConverter
    fun fromEpisodeJson(json: String): List<String>{
        return jsonParser.fromJson<ArrayList<String>>(
            json,
            object : TypeToken<ArrayList<String>>(){}.type
        ) ?: emptyList()
    }

    @TypeConverter
    fun toEpisodeJson(result: List<String>): String {
        return jsonParser.toJson(
            result,
            object : TypeToken<ArrayList<String>>(){}.type
        ) ?: "[]"
    }

    @TypeConverter
    fun fromResultJson(json: String): List<ResultInfo>{
        return jsonParser.fromJson<ArrayList<ResultInfo>>(
            json,
            object : TypeToken<ArrayList<ResultInfo>>(){}.type
        ) ?: emptyList()
    }

    @TypeConverter
    fun toResultJson(episode: List<ResultInfo>): String {
        return jsonParser.toJson(
            episode,
            object : TypeToken<ArrayList<ResultInfo>>(){}.type
        ) ?: "[]"
    }
}