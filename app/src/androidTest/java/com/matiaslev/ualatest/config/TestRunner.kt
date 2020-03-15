package com.matiaslev.ualatest.config

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner
import com.matiaslev.ualatest.presentation.UalaTestApp

class TestRunner: AndroidJUnitRunner() {
    override fun newApplication(cl: ClassLoader?, className: String?, context: Context?): Application {
        return super.newApplication(
            cl, UalaTestApp::class.java.name, context
        )
    }
}