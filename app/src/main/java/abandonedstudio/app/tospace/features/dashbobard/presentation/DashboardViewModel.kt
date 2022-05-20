package abandonedstudio.app.tospace.features.dashbobard.presentation

import abandonedstudio.app.tospace.core.domain.util.extension.showToast
import abandonedstudio.app.tospace.features.dashbobard.domain.DataSource
import abandonedstudio.app.tospace.features.dashbobard.data.FacilityWeather
import abandonedstudio.app.tospace.features.dashbobard.data.SpaceXLaunch
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import javax.inject.Inject
import kotlin.math.abs

@HiltViewModel
class DashboardViewModel @Inject constructor(
    application: Application,
    private val dataSource: DataSource
) : AndroidViewModel(application) {

    val nextLaunchFlow: StateFlow<SpaceXLaunch?> by lazy {
        flow {
            try {
                emit(dataSource.loadNextLaunch())
            } catch (e: Exception) {
                //TODO: emit empty maybe?
                e.localizedMessage?.run {
                    showToast(this)
                }
            }
        }
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), null)
    }

    val nextLaunchCountdown: StateFlow<String> by lazy {
        nextLaunchFlow
            .map { it?.timeStamp }
            .filterNotNull()
            .flatMapLatest { launchTime ->
                flow {
                    while (true) {
                        val now = System.currentTimeMillis() / 1000
                        emit(
                            convertDuration(now = now, launchTime = launchTime)
                        )
                        delay(1000)
                    }
                }
            }
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), "")
    }

    val previousLaunchFlow: StateFlow<SpaceXLaunch?> by lazy {
        flow {
            try {
                emit(dataSource.loadLastLaunch())
            } catch (e: Exception) {
                //TODO: emit empty maybe?
                e.localizedMessage?.run {
                    showToast(this)
                }
            }
        }
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), null)
    }

    val previousLaunchCountdown: StateFlow<String> by lazy {
        previousLaunchFlow
            .map { it?.timeStamp }
            .filterNotNull()
            .flatMapLatest { launchTime ->
                flow {
                    while (true) {
                        val now = System.currentTimeMillis() / 1000
                        emit(
                            convertDuration(now = now, launchTime = launchTime)
                        )
                        delay(1000)
                    }
                }
            }
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), "")
    }

    private fun convertDuration(now: Long, launchTime: Int): String {
        var time = abs(now - launchTime)
        val days = abs(time / 86_400) // 3600 * 24
        time %= 86_400
        val hours = abs(time / 3600)
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
                e.localizedMessage?.run {
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
                e.localizedMessage?.run {
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
                e.localizedMessage?.run {
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