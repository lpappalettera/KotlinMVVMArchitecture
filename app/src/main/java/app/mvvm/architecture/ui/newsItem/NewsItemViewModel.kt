package app.mvvm.architecture.ui.newsItem

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import app.mvvm.architecture.model.NewsItem
import app.mvvm.architecture.repository.NewsRepository
import app.mvvm.architecture.util.Resource
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

// NOTE: Not a HiltViewModel since it uses AssistedInject, which is not yet supported by Hilt.
class NewsItemViewModel @AssistedInject constructor(
    @Assisted private val newsItemId: String,
    private val newsRepository: NewsRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<Resource<NewsItem>>(Resource.Loading())
    val uiState: StateFlow<Resource<NewsItem>> = _uiState

    init {
        loadNewsItem()
    }

    private fun loadNewsItem() {
        viewModelScope.launch {
            newsRepository.getNewsItem(newsItemId).collect { newsItem ->
                _uiState.emit(newsItem)
            }
        }
    }

    @AssistedFactory
    interface Factory {
        fun create(newsItemId: String): NewsItemViewModel
    }

    @Suppress("UNCHECKED_CAST")
    companion object {
        fun provideFactory(
            assistedFactory: Factory,
            newsItemId: String
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return assistedFactory.create(newsItemId) as T
            }
        }
    }
}