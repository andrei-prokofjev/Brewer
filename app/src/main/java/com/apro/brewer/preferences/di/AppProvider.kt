package com.apro.brewer.preferences.di

import android.app.Application

interface AppProvider {
    fun app(): Application
}