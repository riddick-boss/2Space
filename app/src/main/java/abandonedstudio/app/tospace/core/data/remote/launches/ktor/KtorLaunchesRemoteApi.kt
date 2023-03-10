package abandonedstudio.app.tospace.core.data.remote.launches.ktor

import abandonedstudio.app.tospace.core.data.remote.launches.LaunchesRemoteApi
import abandonedstudio.app.tospace.core.data.remote.launches.dto.AllLaunchesResponse
import abandonedstudio.app.tospace.core.data.remote.launches.dto.LaunchDetailedResponse
import abandonedstudio.app.tospace.core.data.remote.launches.dto.LaunchesListResponse
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import javax.inject.Inject

class KtorLaunchesRemoteApi @Inject constructor(
    private val client: HttpClient
) : LaunchesRemoteApi {

    override suspend fun fetchNextLaunch(): LaunchDetailedResponse {
        val response = client.get(Routes.UPCOMING_LAUNCH_URL).body<LaunchesListResponse<LaunchDetailedResponse>>()
        return response.results.first()
    }

    override suspend fun fetchPreviousLaunch(): LaunchDetailedResponse {
        val response = client.get(Routes.PREVIOUS_LAUNCH_URL).body<LaunchesListResponse<LaunchDetailedResponse>>()
        return response.results.first()
    }

    override suspend fun loadUpcomingLaunches(next: String?): AllLaunchesResponse =
        client.get(next ?: Routes.UPCOMING_LAUNCHES_URL).body()
}