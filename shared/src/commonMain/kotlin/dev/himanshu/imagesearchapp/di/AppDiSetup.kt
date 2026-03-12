package dev.himanshu.imagesearchapp.di

import dev.himanshu.imagesearchapp.data.di.getDataModule
import dev.himanshu.imagesearchapp.domain.di.getDomainModule
import dev.himanshu.imagesearchapp.ui.di.getUiModule
import org.koin.core.context.startKoin

fun initKoin(){
    startKoin {
        modules(
            getDataModule(),
            getDomainModule(),
            getUiModule()
        )
    }
}