package abandonedstudio.app.tospace.core.data.remote.spacex.ktor

import abandonedstudio.app.tospace.core.data.remote.spacex.SpaceXRemoteApi
import abandonedstudio.app.tospace.core.data.remote.spacex.dto.query.LaunchQuery
import abandonedstudio.app.tospace.core.data.remote.spacex.dto.response.SpaceXSingleLaunchResponse
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.serialization.json.Json
import javax.inject.Inject

class KtorSpaceXRemoteApi @Inject constructor(
    private val client: HttpClient
) : SpaceXRemoteApi {

    override suspend fun getLastLaunch(): SpaceXSingleLaunchResponse {
        val body = Json.parseToJsonElement(LaunchQuery.LAST)
        return client.post(
            Routes.QUERY
        ) {
            contentType(ContentType.Application.Json)
            setBody(body)
        }.body()
    }

    override suspend fun getNextLaunch(): SpaceXSingleLaunchResponse {
        val body = Json.parseToJsonElement(LaunchQuery.NEXT)
        return client.post(
            Routes.QUERY
        ) {
            contentType(ContentType.Application.Json)
            setBody(body)
        }.body()
    }
}