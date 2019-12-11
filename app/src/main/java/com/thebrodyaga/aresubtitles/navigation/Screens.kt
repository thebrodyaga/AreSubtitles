package com.thebrodyaga.aresubtitles.navigation

import androidx.fragment.app.Fragment
import com.thebrodyaga.aresubtitles.screen.fragments.SubtitleFragment
import com.thebrodyaga.aresubtitles.screen.fragments.settings.all.SettingsFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

object Screens {

    data class SubtitleScreen(val id: Int) : SupportAppScreen() {
        override fun getFragment(): Fragment {
            return SubtitleFragment.newInstance()
        }
    }

    /*object MainScreen : SupportAppScreen() {
        override fun getFragment(): Fragment {
            return MainFragment()
        }
    }

    object SoundsListScreen : SupportAppScreen() {
        override fun getFragment(): Fragment {
            return SoundsListFragment()
        }
    }

    data class SoundsDetailsScreen constructor(val transcription: String) : SupportAppScreen() {
        override fun getFragment(): Fragment {
            return SoundFragment.newInstance(transcription)
        }
    }

    object SoundsTrainingScreen : SupportAppScreen() {
        override fun getFragment(): Fragment {
            return SoundsTrainingFragment()
        }
    }*/

    object SettingsScreen : SupportAppScreen() {
        override fun getFragment(): Fragment {
            return SettingsFragment()
        }
    }
}