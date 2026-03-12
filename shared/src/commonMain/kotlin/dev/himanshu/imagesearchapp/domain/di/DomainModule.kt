package dev.himanshu.imagesearchapp.domain.di

import dev.himanshu.imagesearchapp.domain.useCase.GetImagesUseCase
import org.koin.core.module.Module
import org.koin.dsl.module

fun getDomainModule(): Module{
    return module {
        factory { GetImagesUseCase(get()) }
    }
}