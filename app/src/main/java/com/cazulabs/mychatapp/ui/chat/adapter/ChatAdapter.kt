package com.cazulabs.mychatapp.ui.chat.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cazulabs.mychatapp.databinding.ItemChatMeBinding
import com.cazulabs.mychatapp.databinding.ItemChatOtherBinding
import com.cazulabs.mychatapp.domain.model.MessageModel

class ChatAdapter(
    private val messageList: MutableList<MessageModel>,
    private val username: String
) : RecyclerView.Adapter<ChatViewHolder>() {

    companion object {
        const val SENT_MESSAGE = 0
        const val RECEIVE_MESSAGE = 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        val binding = when(viewType) {
            SENT_MESSAGE -> {
                ItemChatMeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            }
            RECEIVE_MESSAGE -> {
                ItemChatOtherBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            }
            else -> {
                ItemChatMeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            }
        }

        return ChatViewHolder(binding)
    }

    override fun getItemCount(): Int = messageList.size

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        holder.bind(messageList[position], getItemViewType(position))
    }

    override fun getItemViewType(position: Int): Int {
        return if (messageList[position].user.username == username)
            SENT_MESSAGE
        else
            RECEIVE_MESSAGE
    }


}