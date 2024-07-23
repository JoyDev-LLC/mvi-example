package com.example.mvvm_example.clean.domain.use_cases

import com.example.mvvm_example.clean.data.models.CharacterData
import com.example.mvvm_example.clean.data.repository.ICharactersRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class GetCharactersUseCase(
    private val repository: ICharactersRepository
) {

    suspend operator fun invoke(page: Int): CharacterData {
        return withContext(Dispatchers.IO) {
            delay(1000) //эмитация долгой загрузки
            repository.getCharacters(page)
        }
    }
}
