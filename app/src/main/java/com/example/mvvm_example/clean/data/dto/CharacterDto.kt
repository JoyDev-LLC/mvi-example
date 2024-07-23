package com.example.mvvm_example.clean.data.dto

import com.google.gson.annotations.SerializedName

data class CharacterDto(
    @SerializedName("info")
    val info: CharacterInfoDto,
    @SerializedName("results")
    val results: List<CharacterResultsDto>
)

data class CharacterInfoDto(
    @SerializedName("count")
    val count: Int,
    @SerializedName("pages")
    val pages: Int,
    @SerializedName("next")
    val next: String?,
    @SerializedName("prev")
    val prev: String?
)

data class CharacterResultsDto(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("species")
    val species: String,
    @SerializedName("gender")
    val gender: String,
    @SerializedName("origin")
    val origin: CharacterOriginDto,
    @SerializedName("location")
    val location: CharacterLocationDto,
    @SerializedName("image")
    val image: String
)

data class CharacterOriginDto(
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String
)

data class CharacterLocationDto(
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String
)
