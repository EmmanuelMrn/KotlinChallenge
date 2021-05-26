package com.example.mindteamschallenge.utils

import android.app.Activity

object Util {

    fun isActivityAlive(activity: Activity): Boolean {
        return !activity.isFinishing && !activity.isDestroyed
    }
}