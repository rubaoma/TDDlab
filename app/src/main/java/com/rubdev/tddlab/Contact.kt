package com.rubdev.tddlab

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Contact(
    val name: String,
    val phone: String,
    val prothograph: String
): Parcelable
