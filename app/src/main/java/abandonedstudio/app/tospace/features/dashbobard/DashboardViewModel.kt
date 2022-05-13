package abandonedstudio.app.tospace.features.dashbobard

import abandonedstudio.app.tospace.core.domain.util.extension.showToast
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

    val starBaseWeather: StateFlow<FacilityWeather?> by lazy {
        flow {
            try {
                emit(dataSource.loadCanaveralWeather()) //TODO: starbase
            } catch (e: Exception) {
                e.localizedMessage?.run {
                    showToast(this)
                }
            }
        }
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), null)
    }


    val showWeatherLoading: StateFlow<Boolean> by lazy {
        combine(capeCanaveralWeather, starBaseWeather) { t1, t2 -> t1 == null || t2 == null }
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), true)
    }
}