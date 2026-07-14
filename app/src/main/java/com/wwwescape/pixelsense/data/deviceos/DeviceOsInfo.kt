package com.wwwescape.pixelinfo.data.deviceos

data class DeviceOsInfo(
    val manufacturer: String,
    val brand: String,
    val model: String,
    val codename: String,
    val board: String,
    val hardware: String,
    val androidVersion: String,
    val apiLevel: Int,
    val buildId: String,
    val securityPatch: String,
    val kernelVersion: String,
    val bootloader: String,
    val androidId: String,
    val baseband: String?,
)
