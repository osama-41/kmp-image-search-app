package dev.himanshu.imagesearchapp.ui.di

import dev.himanshu.imagesearchapp.ui.ImageViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.core.module.Module
import org.koin.dsl.module

actual fun getUiModule(): Module {
    return module{
        factory { ImageViewModel(get()) }
    }
}

class ViewModelProvider: KoinComponent{

    fun provideImageViewModel() = ImageViewModel(get())

}
