package com.wwwescape.pixelinfo.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.wwwescape.pixelinfo.ui.components.CategoryGrid
import com.wwwescape.pixelinfo.ui.components.DashboardHero
import com.wwwescape.pixelinfo.ui.components.LiveMetricsSection
import com.wwwescape.pixelinfo.ui.navigation.Destination

/** The tabless Dashboard: a hero device summary, the full category grid, and Live Metrics. */
@Composable
fun OverviewScreen(
    onCategoryClick: (Destination) -> Unit,
    modifier: Modifier = Modifier,
) {
    CategoryGrid(
        categories = Destination.detailScreens,
        onCategoryClick = onCategoryClick,
        modifier = modifier,
        header = { DashboardHero() },
        footer = { LiveMetricsSection() },
    )
}
