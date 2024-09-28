package com.cazulabs.mychatapp.ui.chat.adapter

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.cazulabs.mychatapp.R
import com.cazulabs.mychatapp.databinding.ItemChatMeBinding
import com.cazulabs.mychatapp.databinding.ItemChatOtherBinding
import com.cazulabs.mychatapp.domain.model.MessageModel
import com.cazulabs.mychatapp.ui.chat.adapter.ChatAdapter.Companion.RECEIVE_MESSAGE
import com.cazulabs.mychatapp.ui.chat.adapter.ChatAdapter.Companion.SENT_MESSAGE

class ChatViewHolder(private val binding: ViewBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(messageModel: MessageModel, itemViewType: Int) {
        when (itemViewType) {
            SENT_MESSAGE -> {
                bindSendMessage(messageModel)
            }

            RECEIVE_MESSAGE -> {
                bindReceiveMessage(messageModel)
            }
        }
    }

    private fun bindSendMessage(messageModel: MessageModel) {
        val currentBinding = binding as ItemChatMeBinding
        currentBinding.tvDate.text = messageModel.date
        currentBinding.tvMsg.text = messageModel.msg
        currentBinding.tvHour.text = messageModel.hour
    }

    private fun bindReceiveMessage(messageModel: MessageModel) {
        val currentBinding = binding as ItemChatOtherBinding
        currentBinding.tvDate.text = messageModel.date
        currentBinding.tvUser.text = messageModel.user.username
        currentBinding.tvMsg.text = messageModel.msg
        currentBinding.tvHour.text = messageModel.hour

        if (messageModel.user.admin)
            currentBinding.ivUser
                .setImageDrawable(binding.root.context.getDrawable(R.drawable.ic_admin))
    }
}