package com.cazulabs.mychatapp.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.cazulabs.mychatapp.R
import com.cazulabs.mychatapp.databinding.FragmentMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding
    private val viewModel by viewModels<MainViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        binding.btnChat.setOnClickListener {
            if (!binding.tieUsername.text.isNullOrEmpty()) {
                viewModel.saveUsername(binding.tieUsername.text.toString())
                findNavController().navigate(R.id.action_main_fragment_to_chat_fragment)
            }
        }
        subscribeToState()

        return binding.root
    }

    private fun subscribeToState() {
        lifecycleScope.launch {
            viewModel.uiState.collect { state ->
                when (state) {
                    MainViewState.LOADING -> {
                        binding.progressBar.isVisible = true
                    }

                    MainViewState.REGISTERED -> {
                        findNavController().navigate(R.id.action_main_fragment_to_chat_fragment)
                    }

                    MainViewState.UNREGISTERED -> {
                        binding.progressBar.isVisible = false
                    }
                }
            }
        }
    }

}