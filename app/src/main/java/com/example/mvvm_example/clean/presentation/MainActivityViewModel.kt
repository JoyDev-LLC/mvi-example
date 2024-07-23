package com.example.mvvm_example.clean.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvm_example.clean.data.models.CharacterData
import com.example.mvvm_example.clean.data.models.CharactersButtonItem
import com.example.mvvm_example.clean.domain.use_cases.GetCharactersUseCase
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainActivityViewModel(
    private val charactersUseCase: GetCharactersUseCase
) : ViewModel() {

    private val _uiState: MutableStateFlow<State> = MutableStateFlow(State())
    val uiState = _uiState.asStateFlow()

    private val _uiAction: MutableSharedFlow<Action> = MutableSharedFlow()
    val uiAction = _uiAction.asSharedFlow()

    fun setEffect(action: Effect) {
        when (action) {
            is Effect.LoadCharacters -> getCharacters()
        }
    }

    private fun getCharacters() {
        _uiState.value = uiState.value.copy(isLoading = true)

        viewModelScope.launch {
            try {
                charactersUseCase.invoke(uiState.value.page)
                    .handleCharactersData()
            } catch (e: Throwable) {
                _uiState.value = uiState.value.copy(isLoading = false)
                _uiAction.emit(
                    Action.ShowToastError(e.localizedMessage ?: e.message ?: "error")
                )
            }
        }
    }

    private fun CharacterData.handleCharactersData() {
        if (uiState.value.page < info.pages) {
            _uiState.value = uiState.value.copy(
                characters = uiState.value.characters.dropLast(1)
                    .plus(results)
                    .plus(CharactersButtonItem()),
                isLoading = false,
                page = uiState.value.page + 1
            )
        } else {
            _uiState.value = uiState.value.copy(
                characters = uiState.value.characters.dropLast(1)
                    .plus(results),
                isLoading = false,
                page = uiState.value.page + 1
            )
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.coroutineContext.cancelChildren()
    }
}
