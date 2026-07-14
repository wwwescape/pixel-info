package com.wwwescape.pixelinfo.ui.screens.deviceos

import android.app.Application
import android.os.SystemClock
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.wwwescape.pixelinfo.data.deviceos.DeviceOsInfo
import com.wwwescape.pixelinfo.data.deviceos.DeviceOsRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class DeviceOsViewModel(application: Application) : AndroidViewModel(application) {

    val deviceOsInfo: DeviceOsInfo = DeviceOsRepository.collectStatic(application)

    val uptimeMillis: StateFlow<Long> = DeviceOsRepository.uptimeMillis()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = SystemClock.elapsedRealtime(),
        )
}
