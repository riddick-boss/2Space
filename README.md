# 2Space
#### Android app for every space enthusiast. Check upcoming and past SpaceX launches and find out more about each flight (e.g. data about payload).

#### App is published on Google Play.

<a href="https://play.google.com/store/apps/details?id=abandonedstudio.app.tospace">
    <img alt="Get it on Google Play"
        height="80"
        src="https://play.google.com/intl/en_us/badges/images/generic/en_badge_web_generic.png" />
</a>  

Used:
- Kotlin
- Hilt
- Jetpack Compose
- [Ktor](https://ktor.io/)
- [Accompanist library](https://github.com/google/accompanist)
- Pagination
- Firebase Cloud Messaging
- MVVM architecture
- Git flow workflow

## General info

App is organized in MVVM architecture. Kotlin Coroutines and Flows were used to achieve reactive approach. Data is fetched from apis (like [SpaceX Api](https://github.com/r-spacex/SpaceX-API)) with usage of Ktor (this enables to use code in kotlin multiplatform app in future). Whole app is organized with intent to be clean, scalable and easy to refactor. Also, SOLID rules were applied.

## Screenshots

| ![Dashboard](/screenshots/dashboard.jpg) | ![Launches](/screenshots/launches.jpg) | ![Events](/screenshots/events.jpg) |
|-|-|-|

## Checkout also

Checkout [my other repo](https://github.com/riddick-boss/2SpaceMessagingCenter), which contains server side code of the project. Its purpose is to fetch info about upcoming launches and send FCM notifications if necessary. Android app subscripes to this topic and shows notifications. Written in [Go](https://go.dev/).

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
                        println("CustomKtorHttpLogger: $message")
                    }

                }
            }
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                })
            }
        }
        api = KtorLaunchesRemoteApi(httpClient)
    }

    @After
    fun tearDown() {
        httpClient.close()
    }

    @Test
    fun `fetching and parsing upcoming launches without errors`() = runBlocking {
        var exception: Exception? = null
        try {
            val data = api.loadUpcomingLaunches(null)
        } catch (e: Exception) {
            exception = e
        }
        Truth.assertThat(exception).isNull()
    }
```

## Disclaimer

Author and app is not affiliated, associated, authorized, endorsed by, or in any way officially connected with Space Exploration Technologies Corp (SpaceX), or any of its subsidiaries or its affiliates. The names SpaceX as well as related names, marks, emblems and images are registered trademarks of their respective owners. This is not an official SpaceX app.
