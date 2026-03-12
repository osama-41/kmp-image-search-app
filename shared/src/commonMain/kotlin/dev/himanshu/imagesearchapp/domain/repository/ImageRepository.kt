package dev.himanshu.imagesearchapp.domain.repository

import dev.himanshu.imagesearchapp.domain.model.Image

interface ImageRepository {

    suspend fun getImages(q: String): Result<List<Image>>
}
