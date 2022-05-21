package abandonedstudio.app.tospace.core.data.remote.spacex.ktor

import abandonedstudio.app.tospace.BuildConfig
import android.util.Log
import com.google.common.truth.Truth
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json
import org.junit.After
import org.junit.Before
import org.junit.Test

class KtorSpaceXRemoteApiTest {

    private lateinit var httpClient: HttpClient
    private lateinit var api: KtorSpaceXRemoteApi

    @Before
    fun setUp() {
        httpClient = HttpClient(Android) {
            install(Logging) {
                level = if(BuildConfig.DEBUG) LogLevel.ALL else LogLevel.NONE
                logger = object : Logger {
                    override fun log(message: String) {
                        Log.i("CustomKtorHttpLogger", message)
                    }

                }
            }
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                })
            }
        }
        api = KtorSpaceXRemoteApi(httpClient)
    }

    @After
    fun tearDown() {
        httpClient.close()
    }

    @Test
    fun fetchingLastLaunchProperly() = runBlocking {
        var exception: Exception? = null
        try {
            val data = api.getLastLaunch()
            println(data.toString())
        } catch (e: Exception) {
            exception = e
        }
        Truth.assertThat(exception).isNull()
    }

    @Test
    fun fetchingNextLaunchProperly() = runBlocking {
        var exception: Exception? = null
        try {
            val data = api.getNextLaunch()
            println(data.toString())
        } catch (e: Exception) {
            exception = e
        }
        Truth.assertThat(exception).isNull()
    }
}