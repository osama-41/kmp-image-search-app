package dev.himanshu.imagesearchapp.ui.di

import dev.himanshu.imagesearchapp.ui.ImageViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

actual fun getUiModule(): Module {
    return module {
        viewModel { ImageViewModel(get()) }
    }
}