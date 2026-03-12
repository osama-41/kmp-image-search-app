package dev.himanshu.imagesearchapp.data.model

import kotlinx.serialization.Serializable

@Serializable
data class PixabayResponse(
    val hits: List<HitDTO>,
    val total: Int,
    val totalHits: Int
)