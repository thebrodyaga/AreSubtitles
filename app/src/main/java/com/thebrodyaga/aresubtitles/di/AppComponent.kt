package com.thebrodyaga.aresubtitles.di

import android.app.Application
import com.google.gson.Gson
import com.thebrodyaga.aresubtitles.di.modules.AppModule
import com.thebrodyaga.aresubtitles.tools.SettingManager
import com.thebrodyaga.aresubtitles.app.AppActivity
import com.thebrodyaga.aresubtitles.app.SplashActivity
import com.thebrodyaga.aresubtitles.di.modules.NavigationModule
import com.thebrodyaga.aresubtitles.navigation.RouterTransition
import com.thebrodyaga.aresubtitles.screen.fragments.settings.all.SettingsFragment
import com.thebrodyaga.aresubtitles.screen.fragments.subtitle.details.SubtitleFragment
import dagger.BindsInstance
import dagger.Component
import ru.terrakok.cicerone.NavigatorHolder
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, NavigationModule::class])
interface AppComponent {
    fun getRouter(): RouterTransition
    fun getNavigatorHolder(): NavigatorHolder
    fun getGson(): Gson
    fun inject(activity: AppActivity)
    fun inject(activity: SplashActivity)
    fun getSettingManager(): SettingManager
    fun inject(fragment: SettingsFragment)
    fun inject(fragment: SubtitleFragment)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}