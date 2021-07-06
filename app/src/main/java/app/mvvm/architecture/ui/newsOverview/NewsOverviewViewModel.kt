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
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class NewsOverviewViewModel @Inject constructor(
    private val newsRepository: NewsRepository
) : ViewModel() {
    init {
        loadNews()
    }

    private val _state = MutableStateFlow<Resource<List<NewsItem>>>(Resource.Loading())
    val state: StateFlow<Resource<List<NewsItem>>> = _state

    fun loadNews() {
        viewModelScope.launch {
            newsRepository.getNews().collect { news ->
                _state.emit(news)
            }
        }
    }
}