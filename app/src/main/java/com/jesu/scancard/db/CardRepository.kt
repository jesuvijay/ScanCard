package com.jesu.scancard.db

import androidx.annotation.WorkerThread

class CardRepository(private val businessCardDao: BusinessCardDao) {


    @WorkerThread
    suspend fun insert(businessCard: BusinessCard) {
        businessCardDao.insert(businessCard)
    }
}