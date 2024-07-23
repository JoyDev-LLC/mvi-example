package com.example.mvvm_example.di

import com.example.mvvm_example.clean.data.repository.GetCharactersRepository
import com.example.mvvm_example.clean.data.repository.ICharactersRepository
import com.example.mvvm_example.clean.domain.use_cases.GetCharactersUseCase
import org.koin.dsl.module

val networkModule = module {
    single { getApiService() }
    factory<ICharactersRepository> { GetCharactersRepository(get()) }
    factory { GetCharactersUseCase(get()) }
}
