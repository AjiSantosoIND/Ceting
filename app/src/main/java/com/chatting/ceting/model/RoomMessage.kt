package com.chatting.ceting.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class RoomMessage(
    val first_participant: String,
    val second_participant: String,
    val created_date: Date,
    val is_deleted: Boolean
) : Parcelable {
    constructor() : this("", "", Date(), false)
}