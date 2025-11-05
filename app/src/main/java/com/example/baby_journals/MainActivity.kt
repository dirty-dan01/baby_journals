package com.example.baby_journals

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import com.example.baby_journals.data.local.BabyLocalData
import com.example.baby_journals.data.repo.BabyRepositoryImpl
import com.example.baby_journals.ui.AppNav
import com.example.baby_journals.ui.BabyViewModel
import com.example.baby_journals.ui.theme.Baby_journalsTheme

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val repo = BabyRepositoryImpl(BabyLocalData(this))
        val vm = BabyViewModel(repo)
        enableEdgeToEdge()
        setContent {
            Baby_journalsTheme { AppNav(vm) }
        }
    }
}
