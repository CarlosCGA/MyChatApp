package com.cazulabs.mychatapp.ui.chat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.cazulabs.mychatapp.R
import com.cazulabs.mychatapp.databinding.FragmentChatBinding
import com.cazulabs.mychatapp.ui.chat.adapter.ChatAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ChatFragment : Fragment() {

    private lateinit var binding: FragmentChatBinding
    private val viewModel by viewModels<ChatViewModel>()

    private lateinit var chatAdapter: ChatAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentChatBinding.inflate(inflater, container, false)

        setUpUI()

        return binding.root
    }

    private fun setUpUI() {
        binding.ivBack.setOnClickListener {
            viewModel.logOut {
                findNavController().navigate(R.id.action_chat_back)
            }
        }

        binding.tvUsername.text =
            getString(R.string.welcome_username, getString(R.string.beautiful_user))

        binding.btnSendMsg.setOnClickListener {
            val msg = binding.etMsg.text.toString()
            if (msg.isNotEmpty()) {
                viewModel.sendMessage(msg)
                binding.etMsg.text.clear()
            }
        }

        subscribeToState()
    }

    private fun subscribeToState() {
        lifecycleScope.launch {
            viewModel.uiState.collect { state ->
                when (state) {
                    ChatViewState.LOADING -> {
                        binding.progressBar.isVisible = true
                    }

                    ChatViewState.LOADED -> {
                        binding.progressBar.isVisible = false
                        binding.tvUsername.text =
                            getString(R.string.welcome_username, viewModel.username)
                        setUpMessages()
                        subscribeToMessage()
                    }
                }
            }
        }
    }

    private fun setUpMessages() {
        chatAdapter = ChatAdapter(mutableListOf(), viewModel.username)
        binding.rvChat.apply {
            adapter = chatAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    private fun subscribeToMessage() {
        lifecycleScope.launch {
            viewModel.messageList.collect {
                chatAdapter.updateList(it.toMutableList())
                binding.rvChat.scrollToPosition(chatAdapter.itemCount - 1)
            }
        }
    }

}