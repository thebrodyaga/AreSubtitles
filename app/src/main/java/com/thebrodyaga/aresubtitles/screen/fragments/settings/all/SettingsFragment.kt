package com.thebrodyaga.aresubtitles.screen.fragments.settings.all


import android.os.Bundle
import android.view.View
import android.widget.CompoundButton
import com.jakewharton.rxbinding3.widget.checkedChanges

import com.thebrodyaga.aresubtitles.R
import com.thebrodyaga.aresubtitles.app.App
import com.thebrodyaga.aresubtitles.app.AppActivity
import com.thebrodyaga.aresubtitles.app.BaseActivity
import com.thebrodyaga.aresubtitles.screen.appbarBottomPadding
import com.thebrodyaga.aresubtitles.screen.base.BaseFragment
import com.thebrodyaga.aresubtitles.screen.isGone
import com.thebrodyaga.aresubtitles.screen.isSystemDarkMode
import com.thebrodyaga.aresubtitles.tools.SettingManager
import kotlinx.android.synthetic.main.fragment_settings.*
import javax.inject.Inject

class SettingsFragment : BaseFragment() {

    @Inject
    lateinit var settingManager: SettingManager

    override fun getLayoutId(): Int = R.layout.fragment_settings

    override fun onCreate(savedInstanceState: Bundle?) {
        App.appComponent.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.appbarBottomPadding()
        toolbar.setNavigationOnClickListener { getAnyRouter().exit() }
        setThemeSetting()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        system_theme.setOnCheckedChangeListener(null)
        is_dark_theme.setOnCheckedChangeListener(null)
    }

    private fun setThemeSetting() {
        when (settingManager.getCurrentTheme()) {
            SettingManager.CurrentTheme.SYSTEM -> {
                system_theme.isChecked = true
                is_dark_theme.isGone(true)
            }
            SettingManager.CurrentTheme.DARK -> {
                system_theme.isChecked = false
                is_dark_theme.isGone(false)
                is_dark_theme.isChecked = true
            }
            SettingManager.CurrentTheme.LIGHT -> {
                system_theme.isChecked = false
                is_dark_theme.isGone(false)
                is_dark_theme.isChecked = false
            }
        }

        unSubscribeOnDestroy(
            system_theme.checkedChanges()
                .skipInitialValue()
                .subscribe { onSystemThemeListener.invoke(it) }
        )

        unSubscribeOnDestroy(
            is_dark_theme.checkedChanges()
                .skipInitialValue()
                .subscribe { onIsDarkThemeListener.invoke(it) }
        )
    }

    private val onSystemThemeListener = { isChecked: Boolean ->
        if (isChecked) {
            settingManager.setCurrentTheme(SettingManager.CurrentTheme.SYSTEM)
            is_dark_theme.isGone(true)
        } else {
            val isSystemDark = context?.isSystemDarkMode() ?: false
            is_dark_theme.isChecked = !isSystemDark
            settingManager.setCurrentTheme(
                if (isSystemDark)
                    SettingManager.CurrentTheme.LIGHT
                else SettingManager.CurrentTheme.DARK
            )
            is_dark_theme.isGone(false)
        }
        App.app.updateTheme()
    }

    private val onIsDarkThemeListener = { isChecked: Boolean ->
        settingManager.setCurrentTheme(
            if (isChecked) SettingManager.CurrentTheme.DARK
            else SettingManager.CurrentTheme.LIGHT
        )
        App.app.updateTheme()
    }
}
