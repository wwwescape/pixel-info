package com.wwwescape.pixelinfo.ui.screens.network

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.wwwescape.pixelinfo.data.network.NetworkRepository
import com.wwwescape.pixelinfo.data.network.NetworkSnapshot
import com.wwwescape.pixelinfo.data.network.NetworkThroughput
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

private const val MAX_THROUGHPUT_HISTORY = 30

class NetworkViewModel(application: Application) : AndroidViewModel(application) {

    private val _snapshot = MutableStateFlow(NetworkRepository.snapshot(application))
    val snapshot: StateFlow<NetworkSnapshot> = _snapshot.asStateFlow()

    val throughput: StateFlow<NetworkThroughput> = NetworkRepository.trafficUpdates()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = NetworkThroughput(downMbps = 0f, upMbps = 0f),
        )

    private val _throughputHistory = MutableStateFlow<List<NetworkThroughput>>(emptyList())
    val throughputHistory: StateFlow<List<NetworkThroughput>> = _throughputHistory.asStateFlow()

    init {
        viewModelScope.launch {
            NetworkRepository.networkChangeEvents(application).collect { refresh() }
        }
        viewModelScope.launch {
            throughput.collect { sample ->
                _throughputHistory.value = (_throughputHistory.value + sample).takeLast(MAX_THROUGHPUT_HISTORY)
            }
        }
    }

    /** Called on active-network change, and also after a permission grant/deny result. */
    fun refresh() {
        _snapshot.value = NetworkRepository.snapshot(getApplication())
    }
}
