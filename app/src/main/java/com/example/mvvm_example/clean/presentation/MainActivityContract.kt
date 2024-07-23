package com.example.mvvm_example.clean.presentation

import com.example.mvvm_example.clean.data.models.BaseCharactersItem

sealed interface Effect {
    object LoadCharacters : Effect
}

data class State(
    val characters: List<BaseCharactersItem> = emptyList(),
    val isLoading: Boolean = false,
    val page: Int = 1
)

sealed interface Action {
    data class ShowToastError(val text: String) : Action
}
