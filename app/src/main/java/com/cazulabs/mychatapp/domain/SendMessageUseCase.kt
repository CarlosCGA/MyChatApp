package com.cazulabs.mychatapp.domain

import android.util.Log
import com.cazulabs.mychatapp.data.network.FirebaseChatService
import com.cazulabs.mychatapp.data.network.dto.MessageDTO
import com.cazulabs.mychatapp.data.network.dto.UserDTO
import java.util.Calendar
import javax.inject.Inject

class SendMessageUseCase @Inject constructor(private val service: FirebaseChatService) {

    operator fun invoke(msg: String, username: String) {
        Log.d("SEND_MESSAGE", "By $username")
        val calendar: Calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val min = getMinute(calendar)


        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val month = calendar.get(Calendar.MONTH) + 1 //January is 0, so we have to add 1
        val year = calendar.get(Calendar.YEAR)

        val userDTO = UserDTO(username, false)

        val messageDTO = MessageDTO(
            msg = msg,
            hour = "$hour:$min",
            date = "$day/$month/$year",
            user = userDTO
        )

        service.sendMessage(messageDTO)
    }

    private fun getMinute(calendar: Calendar): String {
        val min = calendar.get(Calendar.MINUTE)
        return if (min < 10)
            "0$min"
        else min.toString()
    }
}