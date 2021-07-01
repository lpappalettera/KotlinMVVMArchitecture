package app.mvvm.architecture.ui.newsOverview

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import app.mvvm.architecture.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NewsOverviewViewModel @Inject constructor(
    newsRepository: NewsRepository
) : ViewModel() {

    val newsLiveData = newsRepository.getNews().asLiveData(viewModelScope.coroutineContext)

}