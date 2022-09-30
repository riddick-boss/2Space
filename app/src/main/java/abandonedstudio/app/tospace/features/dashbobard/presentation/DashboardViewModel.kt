package abandonedstudio.app.tospace.features.dashbobard.presentation

import abandonedstudio.app.tospace.core.domain.util.extension.showToast
import abandonedstudio.app.tospace.core.domain.util.extension.toMessage
import abandonedstudio.app.tospace.features.dashbobard.domain.DataSource
import abandonedstudio.app.tospace.features.dashbobard.data.FacilityWeather
import abandonedstudio.app.tospace.features.dashbobard.data.SpaceXLaunch
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.abs

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class DashboardViewModel @Inject constructor(
    application: Application,
    private val dataSource: DataSource
) : AndroidViewModel(application) {

    companion object {
        private const val ONE_SECOND_IN_MILLIS = 1000L
        private const val SECONDS_IN_DAY = 86_400
        private const val SECONDS_IN_HOUR = 3600
        private const val SECONDS_IN_MINUTE = 60
    }

    private val refreshFlow = MutableSharedFlow<Unit>()

    fun onRefresh() {
        viewModelScope.launch {
            refreshFlow.emit(Unit)
        }
    }

    val refreshCompletedFlow: Flow<Unit> by lazy {
        combine(nextLaunchFlow, previousLaunchFlow) { t1, t2 -> if (t1 != null && t2 != null) Unit }
            .shareIn(viewModelScope, SharingStarted.WhileSubscribed())
    }

    val nextLaunchFlow: StateFlow<Result<SpaceXLaunch>?> by lazy {
        refreshFlow
            .onStart { emit(Unit) }
            .flatMapLatest {
                flow {
                    emit(null)
                    try {
                        emit(Result.success(dataSource.loadNextLaunch()))
                    } catch (e: Exception) {
                        e.toMessage()?.run {
                            showToast(this)
                        }
                        emit(Result.failure(e))
                    }
                }
            }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), null)
    }

    val nextLaunchCountdown: StateFlow<String> by lazy {
        nextLaunchFlow
            .map { it?.getOrNull()?.timeStamp }
            .filterNotNull()
            .flatMapLatest { launchTime ->
                flow {
                    while (true) {
                        emit(
                            convertDurationFromNow(launchTime = launchTime)
                        )
                        delay(ONE_SECOND_IN_MILLIS)
                    }
                }
            }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), "")
    }

    val previousLaunchFlow: StateFlow<Result<SpaceXLaunch>?> by lazy {
        refreshFlow
            .onStart { emit(Unit) }
            .flatMapLatest {
                flow {
                    emit(null)
                    try {
                        emit(Result.success(dataSource.loadLastLaunch()))
                    } catch (e: Exception) {
                        e.toMessage()?.run {
                            showToast(this)
                        }
                        emit(Result.failure(e))
                    }
                }
            }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), null)
    }

    val previousLaunchCountdown: StateFlow<String> by lazy {
        previousLaunchFlow
            .map { it?.getOrNull()?.timeStamp }
            .filterNotNull()
            .flatMapLatest { launchTime ->
                flow {
                    while (true) {
                        emit(
                            convertDurationFromNow(launchTime = launchTime)
                        )
                        delay(ONE_SECOND_IN_MILLIS)
                    }
                }
            }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), "")
    }

    private fun convertDurationFromNow(launchTime: Long): String {
        val now = System.currentTimeMillis()
        var time = abs(now - launchTime) / 1000 // from millis to seconds
        val days = abs(time / SECONDS_IN_DAY) // 60sec * 60min * 24h
        time %= SECONDS_IN_DAY
        val hours = abs(time / SECONDS_IN_HOUR) // 60sec * 60min
        time %= SECONDS_IN_HOUR
        val minutes = abs(time / SECONDS_IN_MINUTE)
        time %= SECONDS_IN_MINUTE
        val seconds = abs(time)
        val prefix = if (launchTime > now) "T-" else "T+"
        return "$prefix${days}d, ${hours}h, ${minutes}min, ${seconds}s"
    }

    val capeCanaveralWeather: StateFlow<FacilityWeather?> by lazy {
        flow {
            try {
                emit(dataSource.loadCanaveralWeather())
            } catch (e: Exception) {
                e.toMessage()?.run {
                    showToast(this)
                }
            }
        }
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), null)
    }

    val starbaseWeather: StateFlow<FacilityWeather?> by lazy {
        flow {
            try {
                emit(dataSource.loadStarbaseWeather())
            } catch (e: Exception) {
                e.toMessage()?.run {
                    showToast(this)
                }
            }
        }
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), null)
    }

    val vandenbergWeather: StateFlow<FacilityWeather?> by lazy {
        flow {
            try {
                emit(dataSource.loadVandenbergWeather())
            } catch (e: Exception) {
                e.toMessage()?.run {
                    showToast(this)
                }
            }
        }
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), null)
    }

    val showWeatherContent: StateFlow<Boolean> by lazy {
        merge(capeCanaveralWeather, starbaseWeather)
            .map { it != null }
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), false)
    }
}