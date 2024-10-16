package com.example.elguabo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.example.elguabo.ui.theme.ElGuaboTheme
import com.example.elguabo.ui.view.MyCityView

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ElGuaboTheme {
                val windowsSize = calculateWindowSizeClass(activity = this)
                val layoutDirection = LocalLayoutDirection.current
                Surface(
                    modifier = Modifier.padding(
                        start = WindowInsets.safeDrawing.asPaddingValues()
                            .calculateStartPadding(layoutDirection),
                        end = WindowInsets.safeDrawing.asPaddingValues()
                            .calculateEndPadding(layoutDirection)
                    ),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyCityView(
                        windowsSize = windowsSize.widthSizeClass,
                        onBackPressed = { finish() }
                    )
                }
            }
        }
    }
}

@Preview(device = Devices.PHONE)
@Composable
private fun MyCityViewWithPhonePreview(){
    ElGuaboTheme {
        Surface{
            MyCityView(
                windowsSize = WindowWidthSizeClass.Compact,
                onBackPressed = {}
            )
        }
    }
}

@Preview(device = Devices.TABLET)
@Composable
private fun MyCityViewWithTabletPreview(){
    ElGuaboTheme {
        Surface{
            MyCityView(
                windowsSize = WindowWidthSizeClass.Expanded,
                onBackPressed = {}
            )
        }
    }
}