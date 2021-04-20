package app.mvvm.architecture.ui.newsOverview

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import app.mvvm.architecture.repository.NewsRepository

class NewsOverviewViewModel(
    newsRepository: NewsRepository
) : ViewModel() {

    val newsLiveData = newsRepository.getNews().asLiveData(viewModelScope.coroutineContext)

}