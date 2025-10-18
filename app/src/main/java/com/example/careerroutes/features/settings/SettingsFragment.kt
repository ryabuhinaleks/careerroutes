package com.example.careerroutes.features.settings

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.careerroutes.databinding.FragmentSettingsBinding
import com.example.careerroutes.root.RootActivity
import com.example.core.BaseFragment
import com.google.android.material.snackbar.Snackbar


class SettingsFragment : BaseFragment() {

    private val binding by lazy { FragmentSettingsBinding.inflate(layoutInflater) }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        super.onViewCreated(view, savedInstanceState)
        setLocaleDefault()
        setListener()
    }

    private fun setListener() = with(binding) {
        btnApply.setOnClickListener {
            when (languageRadioGroup.checkedRadioButtonId) {
                binding.radioRussian.id -> changeLanguage(RU)
                binding.radioEnglish.id -> changeLanguage(EN)
            }
            Snackbar.make(
                binding.root,
                getString(com.example.core.R.string.settings_applied),
                Snackbar.LENGTH_SHORT
            ).show()
        }
    }

    private fun setLocaleDefault() = with(binding) {
        val currentLanguage = LocaleManager.getCurrentLanguage(requireContext())
        when (currentLanguage) {
            RU -> radioRussian.isChecked = true
            EN -> radioEnglish.isChecked = true
        }
    }

    private fun changeLanguage(language: String) {
        LocaleManager.setLocale(requireContext(), language)

        parentFragmentManager.popBackStack()

        Handler(Looper.getMainLooper()).postDelayed({
            (activity as? RootActivity)?.restartActivity()
        }, 400)
    }

    companion object {
        private const val RU = "ru"
        private const val EN = "en"
    }
}