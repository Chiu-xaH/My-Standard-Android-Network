package com.example.pagingsample.ui.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.pagingsample.ui.utils.AnimationCardListItem
import com.example.pagingsample.viewmodel.NetworkViewModel

@Composable
fun ReposUI(vm: NetworkViewModel) {
    val response by vm.repoSearchResult.collectAsState()
    var loading by remember { mutableStateOf(true) }

    LaunchedEffect(response) {
        if (response == null) {
            loading = true
            vm.searchRepositories("Android")
        } else {
            loading = false
        }
    }

    if(loading) {
        Box(modifier = Modifier.fillMaxSize()) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    } else {
        val repos = response?.items ?: emptyList()
        LazyColumn(modifier = Modifier.statusBarsPadding().navigationBarsPadding()) {
            items(repos.size) { index ->
                val item = repos[index]
                AnimationCardListItem(
                    headlineContent = {
                        Text(item.name)
                    },
                    supportingContent = item.description?.let {
                        { Text(it) }
                    },
                    index = index
                )
            }
        }
    }
}

