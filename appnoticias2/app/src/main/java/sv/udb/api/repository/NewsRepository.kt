package sv.edu.api.repository

import sv.edu.api.data.remote.RetrofitClient

class NewsRepository {
    private val api = RetrofitClient.api

    suspend fun getTopHeadlines() = api.getTopHeadlines()
}
