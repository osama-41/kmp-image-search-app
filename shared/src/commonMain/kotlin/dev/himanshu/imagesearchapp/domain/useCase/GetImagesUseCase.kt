package dev.himanshu.imagesearchapp.domain.useCase

import dev.himanshu.imagesearchapp.domain.repository.ImageRepository

class GetImagesUseCase(private val imageRepository: ImageRepository) {

    suspend operator fun invoke(q: String) = imageRepository.getImages(q)

}
