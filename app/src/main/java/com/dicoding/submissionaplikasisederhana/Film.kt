package com.dicoding.submissionaplikasisederhana

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Film(
    val name: String,
    val description: String,
    val photo: Int
) : Parcelable
