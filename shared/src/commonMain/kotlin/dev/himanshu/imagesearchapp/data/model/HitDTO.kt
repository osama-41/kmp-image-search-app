package dev.himanshu.imagesearchapp.data.model

import kotlinx.serialization.Serializable

@Serializable
data class HitDTO(
    val id: Int,
    val previewURL: String,
)