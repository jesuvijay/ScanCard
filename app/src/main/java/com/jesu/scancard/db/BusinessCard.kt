package com.jesu.scancard.db

import android.accounts.AuthenticatorDescription
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.w3c.dom.Comment

@Entity
data class BusinessCard(
    @PrimaryKey val uid: Int,
    @ColumnInfo(name = "Company name") val companyName: String,
    @ColumnInfo(name = "Description") val description: String,
    @ColumnInfo(name = "Comment") val comment: String,
    @ColumnInfo(name = "DateTime") val dateTime: String
) {
}