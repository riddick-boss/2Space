# 2Space
#### Android app for every space enthusiast. Check upcoming and past SpaceX launches and find out more about each flight (e.g. data about payload).

#### App is published on Google Play [here](https://play.google.com/store/apps/details?id=abandonedstudio.app.tospace).

Used:
- Kotlin
- Hilt
- Jetpack Compose
- [Ktor](https://ktor.io/)
- [Accompanist library](https://github.com/google/accompanist)
- Pagination
- MVVM architecture
- Git flow workflow

## General info

App is organized in MVVM architecture. Kotlin Flows were used to achieve reactive approach. Data is fetched from [SpaceX Api](https://github.com/r-spacex/SpaceX-API) with usage of Ktor (this enables to use code in kotlin multiplatform app in future). Whole app is organized with intent to create archtecture like in multi-module approach - that is why there are packages like: data, domain, presentation/feature. Also, SOLID rules were applied.

## Tests

Tests were created with usage of Truth library.

Sample test:
```kotlin

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
```

## Disclaimer

Author and app is not affiliated, associated, authorized, endorsed by, or in any way officially connected with Space Exploration Technologies Corp (SpaceX), or any of its subsidiaries or its affiliates. The names SpaceX as well as related names, marks, emblems and images are registered trademarks of their respective owners. This is not an official SpaceX app.
