package dev.himanshu.imagesearchapp.data.repository

import dev.himanshu.imagesearchapp.data.model.PixabayResponse
import dev.himanshu.imagesearchapp.domain.model.Image
import dev.himanshu.imagesearchapp.domain.repository.ImageRepository
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class ImageRepositoryImpl(
    private val httpClient: HttpClient
) : ImageRepository {
    override suspend fun getImages(q: String): Result<List<Image>> {
        return try {
            val response = httpClient.get("api/") {
                url {
                    parameters.append("key", "40308333-07c19e899666cb68334ed3a46")
                    parameters.append("q", q)
                }
            }.body<PixabayResponse>()

            val domainLayerModelList = response.hits.map { hitDTO ->
                Image(
                    id = hitDTO.id,
                    imageUrl = hitDTO.previewURL
                )
            }
            Result.success(domainLayerModelList)

        } catch (e: Exception) {
            return Result.failure(e)
        }
    }
}