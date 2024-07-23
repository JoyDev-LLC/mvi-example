package com.example.mvvm_example.clean.data.mapers

import com.example.mvvm_example.clean.data.dto.CharacterDto
import com.example.mvvm_example.clean.data.dto.CharacterInfoDto
import com.example.mvvm_example.clean.data.dto.CharacterLocationDto
import com.example.mvvm_example.clean.data.dto.CharacterOriginDto
import com.example.mvvm_example.clean.data.dto.CharacterResultsDto
import com.example.mvvm_example.clean.data.models.CharacterData
import com.example.mvvm_example.clean.data.models.CharacterInfo
import com.example.mvvm_example.clean.data.models.CharacterLocation
import com.example.mvvm_example.clean.data.models.CharacterOrigin
import com.example.mvvm_example.clean.data.models.CharacterResults

fun CharacterDto.toModel() = CharacterData(
    info = info.toModel(),
    results = results.map { characterResultsDto ->
        characterResultsDto.toModel()
    }
)

fun CharacterInfoDto.toModel() = CharacterInfo(
    count = count,
    pages = pages,
    next = next,
    prev = prev
)

fun CharacterResultsDto.toModel() = CharacterResults(
    id = id,
    name = name,
    status = status,
    species = species,
    gender = gender,
    origin = origin.toModel(),
    location = location.toModel(),
    image = image
)

fun CharacterOriginDto.toModel() = CharacterOrigin(
    name = name,
    url = url
)

fun CharacterLocationDto.toModel() = CharacterLocation(
    name = name,
    url = url
)
