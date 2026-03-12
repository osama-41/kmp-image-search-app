package dev.himanshu.imagesearchapp.data.client

import io.ktor.client.HttpClient
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.URLProtocol
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

object HttpClientFactory {
    fun getInstance(): HttpClient{
        return HttpClient {
            install(ContentNegotiation){
                json(
                    Json {
                        ignoreUnknownKeys = true
                    }
                )
            }

            install(HttpTimeout){
                requestTimeoutMillis = 3000
                connectTimeoutMillis = 3000
                socketTimeoutMillis = 3000
            }

            install(DefaultRequest) {
                url{
                    host = "pixabay.com"
                    protocol = URLProtocol.HTTPS
                }
            }
        }
    }
}