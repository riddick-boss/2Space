package abandonedstudio.app.tospace.features.news.presentation

import abandonedstudio.app.tospace.core.domain.util.extension.showToast
import abandonedstudio.app.tospace.core.domain.util.extension.toMessage
import abandonedstudio.app.tospace.features.news.data.Article
import abandonedstudio.app.tospace.features.news.data.Event
import abandonedstudio.app.tospace.features.news.domain.DataSource
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    application: Application,
    private val dataSource: DataSource
) : AndroidViewModel(application) {

    val upcomingLaunchesFlow: StateFlow<Result<List<Event>>?> by lazy {
        flow {
            try {
                emit(Result.success(dataSource.loadUpcomingLaunches()))
            } catch (e: Exception) {
                e.toMessage()?.run {
                    showToast(this)
                }
                emit(Result.failure(e))
            }
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), null)
    }

    val articles: StateFlow<Result<List<Article>>?> by lazy {
        flow {
            try {
                emit(Result.success(dataSource.loadArticles()))
            } catch (e: Exception) {
                e.toMessage()?.run {
                    showToast(this)
                }
                emit(Result.failure(e))
            }
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), null)
    }
}