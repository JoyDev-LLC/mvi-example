package com.example.mvvm_example.clean.presentation

import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.mvvm_example.databinding.ActivityMainBinding
import com.example.mvvm_example.clean.presentation.adapter.CharactersAdapter
import kotlinx.coroutines.launch
import org.koin.androidx.scope.RetainedScopeActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : RetainedScopeActivity() {

    private val viewModel by viewModel<MainActivityViewModel>()

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val adapter by lazy {
        CharactersAdapter { viewModel.setEffect(Effect.LoadCharacters) }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        with(binding) {
            setContentView(root)
            charactersRv.adapter = adapter
        }

        initMviObservers()
        viewModel.setEffect(Effect.LoadCharacters)
    }

    private fun initMviObservers() = lifecycleScope.launch {
        repeatOnLifecycle(Lifecycle.State.RESUMED) {
            viewModel.uiState.collect { state -> state.handleState() }
        }
        repeatOnLifecycle(Lifecycle.State.RESUMED) {
            viewModel.uiAction.collect { action -> action.handleAction() }
        }
    }

    private fun State.handleState() {
        binding.progressBarContainer.isVisible = isLoading
        adapter.submitList(characters)
    }

    private fun Action.handleAction() {
        when (this) {
            is Action.ShowToastError -> {
                Toast.makeText(binding.root.context, text, Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

}
