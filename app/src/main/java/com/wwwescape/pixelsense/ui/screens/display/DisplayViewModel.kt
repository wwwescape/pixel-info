package com.wwwescape.pixelinfo.ui.screens.display

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.wwwescape.pixelinfo.data.display.DisplayInfo
import com.wwwescape.pixelinfo.data.display.DisplayRepository

class DisplayViewModel(application: Application) : AndroidViewModel(application) {
    val displayInfo: DisplayInfo = DisplayRepository.collectStatic(application)
}
