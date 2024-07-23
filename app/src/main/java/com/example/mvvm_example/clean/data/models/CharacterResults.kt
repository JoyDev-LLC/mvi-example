package com.example.mvvm_example.clean.data.models

const val CharacterResultsItemType = 0

data class CharacterData(
    val info: CharacterInfo,
    val results: List<CharacterResults>
)

data class CharacterInfo(
    val count: Int,
    val pages: Int,
    val next: String?,
    val prev: String?
)

data class CharacterResults(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val gender: String,
    val origin: CharacterOrigin,
    val location: CharacterLocation,
    val image: String
): BaseCharactersItem(CharacterResultsItemType)

data class CharacterOrigin(
    val name: String,
    val url: String
)

data class CharacterLocation(
    val name: String,
    val url: String
)
