package dev.himanshu.imagesearchapp.ui

import dev.himanshu.imagesearchapp.domain.model.Image

data class ImageUiState (
    val isLoading: Boolean = false,
    val error: String = "",
    val data: List<Image>? = null
)