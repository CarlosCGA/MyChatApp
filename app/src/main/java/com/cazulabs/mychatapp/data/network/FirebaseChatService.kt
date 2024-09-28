package com.cazulabs.mychatapp.data.network

import com.cazulabs.mychatapp.data.network.dto.MessageDTO
import com.cazulabs.mychatapp.data.network.response.MessageResponse
import com.cazulabs.mychatapp.domain.model.MessageModel
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.snapshots
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FirebaseChatService @Inject constructor(private val reference: DatabaseReference) {

    companion object {
        private const val PATH = "messages"
    }

    fun sendMessage(msg:MessageDTO) {
        val newMsg = reference.child(PATH).push()
        newMsg.setValue(msg)
    }

    fun getMessage(): Flow<List<MessageModel>> {
        return reference.child(PATH).snapshots.map { dataSnapshot ->
            dataSnapshot.children.mapNotNull { dataSnapshotNotNull ->
                dataSnapshotNotNull.getValue(MessageResponse::class.java)!!.toDomain()
            }
        }
    }

}