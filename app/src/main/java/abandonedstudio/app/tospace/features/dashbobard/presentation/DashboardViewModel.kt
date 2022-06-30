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
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.abs

@HiltViewModel
class DashboardViewModel @Inject constructor(
    application: Application,
    private val dataSource: DataSource
) : AndroidViewModel(application) {

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
                        delay(1000)
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
                        delay(1000)
                    }
                }
            }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), "")
    }

    private fun convertDurationFromNow(launchTime: Long): String {
        val now = System.currentTimeMillis()
        var time = abs(now - launchTime) / 1000 // from millis to seconds
        val days = abs(time / 86_400) // 60sec * 60min * 24h
        time %= 86_400
        val hours = abs(time / 3600) // 60sec * 60min
        time %= 3600
        val minutes = abs(time / 60)
        time %= 60
        val seconds = abs(time)
        val prefix = if (launchTime > now) "T+" else "T-"
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