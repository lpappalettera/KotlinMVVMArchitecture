package app.mvvm.architecture.ui.newsOverview

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.mvvm.architecture.model.NewsItem
import app.mvvm.architecture.repository.NewsRepository
import app.mvvm.architecture.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsOverviewViewModel @Inject constructor(
    private val newsRepository: NewsRepository
) : ViewModel() {
    
    private val _uiState = MutableStateFlow<Resource<List<NewsItem>>>(Resource.Loading())
    val uiState: StateFlow<Resource<List<NewsItem>>> = _uiState

    init {
        loadNews()
    }

    fun loadNews() {
        viewModelScope.launch {
            newsRepository.getNews().collect { news ->
                _uiState.emit(news)
            }
        }
    }
}