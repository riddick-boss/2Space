package abandonedstudio.app.tospace.core.data.remote.launches.ktor

import abandonedstudio.app.tospace.core.data.remote.launches.LaunchesRemoteApi
import abandonedstudio.app.tospace.core.data.remote.launches.dto.AllLaunchesResponse
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import javax.inject.Inject

class KtorLaunchesRemoteApi @Inject constructor(
    private val client: HttpClient
) : LaunchesRemoteApi {

    override suspend fun loadUpcomingLaunches(next: String?): AllLaunchesResponse =
        client.get(next ?: Routes.UPCOMING_LAUNCHES_URL).body()
}