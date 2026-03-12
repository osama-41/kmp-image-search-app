package dev.himanshu.imagesearchapp.data.di

import dev.himanshu.imagesearchapp.data.client.HttpClientFactory
import dev.himanshu.imagesearchapp.data.repository.ImageRepositoryImpl
import dev.himanshu.imagesearchapp.domain.repository.ImageRepository
import org.koin.core.module.Module
import org.koin.dsl.module

fun getDataModule(): Module {
    return module {
        single{HttpClientFactory.getInstance() }
        factory<ImageRepository> { ImageRepositoryImpl(get()) }
    }
}
