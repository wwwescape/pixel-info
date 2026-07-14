package com.wwwescape.pixelinfo.ui.screens.battery

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.wwwescape.pixelinfo.data.battery.BatteryInfo
import com.wwwescape.pixelinfo.data.battery.BatteryRepository
import com.wwwescape.pixelinfo.data.battery.ThermalInfo
import com.wwwescape.pixelinfo.data.battery.ThermalStatus
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class BatteryViewModel(application: Application) : AndroidViewModel(application) {

    // Event-driven (broadcast/listener), not a polling loop, so it stays live for the
    // whole ViewModel lifetime rather than pausing when the screen isn't observed.
    val batteryInfo: StateFlow<BatteryInfo> = BatteryRepository.batteryUpdates(application)
        .stateIn(viewModelScope, SharingStarted.Eagerly, BatteryRepository.currentBatteryInfo(application))

    val thermalInfo: StateFlow<ThermalInfo> = BatteryRepository.thermalUpdates(application)
        .stateIn(viewModelScope, SharingStarted.Eagerly, ThermalInfo(ThermalStatus.UNAVAILABLE))
}
