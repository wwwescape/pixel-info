package com.wwwescape.pixelinfo.data.memory

data class RamInfo(
    val totalBytes: Long,
    val availableBytes: Long,
    val lowMemoryThresholdBytes: Long,
    val isLow: Boolean,
) {
    val usedBytes: Long get() = totalBytes - availableBytes
}
