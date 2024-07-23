package com.example.mvvm_example.clean.data.repository

import com.example.mvvm_example.clean.data.models.CharacterData

interface ICharactersRepository {
    suspend fun getCharacters(page: Int): CharacterData
}
