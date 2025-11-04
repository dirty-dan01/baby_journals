package com.example.baby_journals

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
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
