package app.mvvm.architecture.ui.newsItem

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
class NewsItemViewModel @Inject constructor(
    private val newsRepository: NewsRepository
) : ViewModel() {

    private val _state = MutableStateFlow<Resource<NewsItem>>(Resource.Loading())
    val state: StateFlow<Resource<NewsItem>> = _state

    fun loadNewsItem(id: String) {
        viewModelScope.launch {
            newsRepository.getNewsItem(id).collect { newsItem ->
                _state.emit(newsItem)
            }
        }
    }
}