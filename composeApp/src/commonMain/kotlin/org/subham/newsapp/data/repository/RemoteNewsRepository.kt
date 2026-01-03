package org.subham.newsapp.data.repository

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.url
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.subham.newsapp.utils.BASE_URL
import org.subham.newsapp.utils.NEWS_API_KEY

class RemoteNewsRepository {
    private val httpClient: HttpClient = HttpClient {
        defaultRequest {
            url(BASE_URL)
            contentType(ContentType.Application.Json)
        }
        install(HttpTimeout) {
            requestTimeoutMillis = 60000
        }
        install(ContentNegotiation) {
            json(
                Json {
                    prettyPrint = true
                    ignoreUnknownKeys = true
                    isLenient = true
                    explicitNulls = false
                }
            )
        }
        install(Logging) {
            logger = Logger.DEFAULT
            level = LogLevel.ALL
            logger = object : Logger {
                override fun log(message: String) {
                    co.touchlab.kermit.Logger.d("KtorClient") {
                        message
                    }
                }

            }

        }
    }

    suspend fun getNews(): HttpResponse {
        return httpClient.get {
            url("top-headlines")
            parameter("country", "us")
            parameter("apiKey", NEWS_API_KEY)
        }
    }

    suspend fun searchNews(query: String): HttpResponse {
        return httpClient.get {
            url("everything")
            parameter("q", query)
            parameter("apiKey", NEWS_API_KEY)
        }
    }
}