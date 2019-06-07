package com.jesu.scancard.db

import androidx.room.Dao
import androidx.room.Insert


@Dao
interface BusinessCardDao{

    @Insert
    suspend fun insert(businessCard: BusinessCard)
}