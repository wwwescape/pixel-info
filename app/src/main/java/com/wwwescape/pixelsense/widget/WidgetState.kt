package com.wwwescape.pixelinfo.widget

import androidx.datastore.preferences.core.stringPreferencesKey

/** Per-widget-instance override of [com.wwwescape.pixelinfo.data.settings.ThemeMode], set via
 * [PixelInfoWidgetConfigActivity] and stored in the widget's own Glance state (not the app's
 * global settings) so each placed widget can pin its own theme. */
val WIDGET_THEME_MODE_KEY = stringPreferencesKey("widget_theme_mode")
