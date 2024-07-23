package com.example.mvvm_example.clean.data.repository

import com.example.mvvm_example.clean.data.NetworkApi
import com.example.mvvm_example.clean.data.mapers.toModel
import com.example.mvvm_example.clean.data.models.CharacterData

class GetCharactersRepository(
    private val networkApi: NetworkApi
) : ICharactersRepository {

    override suspend fun getCharacters(page: Int): CharacterData {
        return networkApi.getDashboardWidgets(page).toModel()
    }
}
