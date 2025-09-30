package sv.edu.api.data.remote

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import sv.edu.api.data.model.NewsResponse

interface NewsApiService {
    @GET("top-headlines")
    suspend fun getTopHeadlines(
        @Query("country") country: String = "us",
        @Query("apiKey") apiKey: String = "db59564b15b84f6fbba8f82378f88f78"
    ): Response<NewsResponse>
}
