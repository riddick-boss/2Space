package abandonedstudio.app.tospace.features.dashbobard

import abandonedstudio.app.tospace.core.domain.util.extension.showToast
import android.app.Application
import android.util.Log
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
                Log.d("dashboardVm", e.toString()) //TODO: del
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
        combine(capeCanaveralWeather, starbaseWeather) { t1, t2 -> t1 != null || t2 != null }
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), false)
    }
}