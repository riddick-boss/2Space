package abandonedstudio.app.tospace.features.dashbobard.presentation

import abandonedstudio.app.tospace.core.domain.util.extension.showToast
import abandonedstudio.app.tospace.features.dashbobard.domain.DataSource
import abandonedstudio.app.tospace.features.dashbobard.data.FacilityWeather
import abandonedstudio.app.tospace.features.dashbobard.data.SpaceXLaunch
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

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
                e.localizedMessage?.run {
                    showToast(this)
                }
            }
        }
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), null)
    }

    val previousLaunchFlow: StateFlow<SpaceXLaunch?> by lazy {
        flow {
            try {
                emit(dataSource.loadLastLaunch())
            } catch (e: Exception) {
                e.localizedMessage?.run {
                    showToast(this)
                }
            }
        }
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), null)
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