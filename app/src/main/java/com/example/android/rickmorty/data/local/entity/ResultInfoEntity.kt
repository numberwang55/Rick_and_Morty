package com.example.android.rickmorty.data.local.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.android.rickmorty.domain.model.Location
import com.example.android.rickmorty.domain.model.Origin
import com.example.android.rickmorty.domain.model.ResultInfo

//@Entity
//data class  CharacterInfoEntity(
//    @PrimaryKey(autoGenerate = false)
//    val id: Int? = null,
//    @Embedded
//    val info: Info,
//    val results: List<Result>
//) {
//    fun toCharacterInfo(): CharacterInfo {
//        return CharacterInfo(
//            info = info,
//            results = results
//        )
//    }
//}

@Entity
data class ResultInfoEntity(
    val created: String,
    val episode: List<String>,
    val gender: String,
    @PrimaryKey val id: Int,
    val image: String,
    @Embedded
    val location: Location,
    val characterName: String,
    @Embedded
    val origin: Origin,
    val species: String,
    val status: String,
    val type: String,
    val characterUrl: String
) {
    fun toResultInfo(): ResultInfo {
        return ResultInfo(
            created = created,
            episode = episode,
            gender = gender,
            id = id,
            image = image,
            location = location,
            name = characterName,
            origin = origin,
            species = species,
            status = status,
            type = type,
            url = characterUrl
        )
    }
}
