package com.cazulabs.mychatapp.ui.chat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.cazulabs.mychatapp.R
import com.cazulabs.mychatapp.databinding.FragmentChatBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChatFragment : Fragment() {

    private lateinit var binding: FragmentChatBinding
    private val viewModel by viewModels<ChatViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentChatBinding.inflate(inflater, container, false)
        binding.ivBack.setOnClickListener {
            findNavController().navigate(R.id.action_chat_back)
        }

        //TODO UPDATE WITH USERNAME
        binding.tvUsername.text = getString(R.string.welcome_username, getString(R.string.beautiful_user))

        binding.btnSendMsg.setOnClickListener {
            val msg = binding.etMsg.text.toString()
            if(msg.isNotEmpty()) {
                viewModel.sendMessage(msg)
                binding.etMsg.text.clear()
            }
        }

        return binding.root
    }


}