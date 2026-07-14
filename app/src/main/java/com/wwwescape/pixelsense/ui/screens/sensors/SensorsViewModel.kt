package com.wwwescape.pixelinfo.ui.screens.sensors

import android.app.Application
import android.hardware.Sensor
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.wwwescape.pixelinfo.data.sensors.SensorInfo
import com.wwwescape.pixelinfo.data.sensors.SensorReading
import com.wwwescape.pixelinfo.data.sensors.SensorsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlin.math.sqrt

private const val MAX_ACCEL_HISTORY = 30

class SensorsViewModel(application: Application) : AndroidViewModel(application) {
    val sensors: List<SensorInfo> = SensorsRepository.listSensors(application)
    val accelerometer: Sensor? = SensorsRepository.defaultAccelerometer(application)
    val accelerometerInfo: SensorInfo? = sensors.firstOrNull { it.sensor == accelerometer }

    private val _accelReading = MutableStateFlow<SensorReading?>(null)
    val accelReading: StateFlow<SensorReading?> = _accelReading.asStateFlow()

    private val _accelHistory = MutableStateFlow<List<Float>>(emptyList())
    val accelHistory: StateFlow<List<Float>> = _accelHistory.asStateFlow()

    init {
        accelerometer?.let { sensor ->
            viewModelScope.launch {
                SensorsRepository.readings(application, sensor).collect { reading ->
                    _accelReading.value = reading
                    val magnitude = sqrt(reading.values.sumOf { (it * it).toDouble() }).toFloat()
                    _accelHistory.value = (_accelHistory.value + magnitude).takeLast(MAX_ACCEL_HISTORY)
                }
            }
        }
    }
}
