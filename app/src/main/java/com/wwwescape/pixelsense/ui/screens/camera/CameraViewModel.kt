package com.wwwescape.pixelinfo.ui.screens.camera

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.wwwescape.pixelinfo.data.camera.CameraLensInfo
import com.wwwescape.pixelinfo.data.camera.CameraRepository
import com.wwwescape.pixelinfo.util.CameraLensRole
import com.wwwescape.pixelinfo.util.classifyLensRoles
import com.wwwescape.pixelinfo.util.mainLens

class CameraViewModel(application: Application) : AndroidViewModel(application) {
    val cameras: List<CameraLensInfo> = CameraRepository.listCameras(application)
    val lensRoles: Map<String, CameraLensRole> = classifyLensRoles(cameras)
    val mainLens: CameraLensInfo? = mainLens(cameras, lensRoles)
}
