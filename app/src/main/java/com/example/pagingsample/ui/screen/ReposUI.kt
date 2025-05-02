package com.example.pagingsample.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.pagingsample.ui.utils.UiState
import com.example.pagingsample.ui.component.AnimationCardListItem
import com.example.pagingsample.ui.component.CommonNetworkScreen
import com.example.pagingsample.ui.component.RefreshIndicator
import com.example.pagingsample.viewmodel.NetworkViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ReposUI(vm: NetworkViewModel) {
    val uiState by vm.repoSearchResult.state.collectAsState()
    val scope = rememberCoroutineScope()
    // 从 uiState 派生是否正在刷新
    val isRefreshing = uiState is UiState.Loading

    val refreshNetwork: suspend () -> Unit = {
        vm.repoSearchResult.clear()
        vm.searchRepositories("Android")
    }

    val pullRefreshState = rememberPullRefreshState(refreshing = isRefreshing, onRefresh = {
        scope.launch {
            refreshNetwork()
        }
    })

    LaunchedEffect(Unit) {
        // 避免旧数据影响
        refreshNetwork()
    }

    Box(modifier = Modifier.fillMaxSize().pullRefresh(pullRefreshState)) {
        CommonNetworkScreen(uiState) {
            val response = (uiState as UiState.Success).data
            val repos = response?.items ?: emptyList()
            LazyColumn(modifier = Modifier.statusBarsPadding().navigationBarsPadding()) {
                items(repos.size, key = { it }) { index ->
                    with(repos[index]) {
                        AnimationCardListItem(
                            headlineContent = { Text(name) },
                            supportingContent = description?.let {
                                { Text(it) }
                            },
                            index = index
                        )
                    }
                }
            }
        }
        RefreshIndicator(isRefreshing, pullRefreshState, Modifier.statusBarsPadding().align(Alignment.TopCenter))
    }
}

