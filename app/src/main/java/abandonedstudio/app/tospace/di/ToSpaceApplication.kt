package abandonedstudio.app.tospace.di

import android.app.Application
import android.content.res.Resources
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ToSpaceApplication : Application() {

    companion object {
        lateinit var resources: Resources
    }

    override fun onCreate() {
        super.onCreate()
        ToSpaceApplication.resources = resources
    }
}