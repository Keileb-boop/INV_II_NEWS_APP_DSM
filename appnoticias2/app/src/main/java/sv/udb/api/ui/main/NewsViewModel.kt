package sv.edu.api.ui.main

import androidx.lifecycle.*
import kotlinx.coroutines.launch
import sv.edu.api.repository.NewsRepository
import sv.edu.api.utils.Resource
import sv.edu.api.data.model.NewsResponse

class NewsViewModel(private val repository: NewsRepository) : ViewModel() {

    private val _news = MutableLiveData<Resource<NewsResponse>>()
    val news: LiveData<Resource<NewsResponse>> = _news

    fun fetchNews() {
        viewModelScope.launch {
            _news.postValue(Resource.Loading())
            try {
                val response = repository.getTopHeadlines()
                if (response.isSuccessful) {
                    _news.postValue(Resource.Success(response.body()!!))
                } else {
                    _news.postValue(Resource.Error("Error: ${response.message()}"))
                }
            } catch (e: Exception) {
                _news.postValue(Resource.Error("No hay conexión"))
            }
        }
    }
}
