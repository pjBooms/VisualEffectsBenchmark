package com.example.visualeffectsbenchmark

import androidx.benchmark.junit4.BenchmarkRule
import androidx.benchmark.junit4.measureRepeated
import androidx.compose.ui.ComposeUiFlags
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import benchmarks.visualeffects.NYContent
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class VisualEffectsBenchmark {

    @get:Rule
    val composeTestRule = createComposeRule()

    @get:Rule
    val benchmarkRule = BenchmarkRule()

    val NUM_OF_FRAMES = 1000

    @OptIn(ExperimentalComposeUiApi::class)
    @Test
    fun benchmarkVisualEffects() {
        ComposeUiFlags.isRectTrackingEnabled = true
        benchmarkRule.measureRepeated {
            composeTestRule.setContent {
                NYContent(NUM_OF_FRAMES)
            }
        }
    }

    @OptIn(ExperimentalComposeUiApi::class)
    @Test
    fun benchmarkVisualEffectsRectTrackingDisabled() {
        ComposeUiFlags.isRectTrackingEnabled = false
        benchmarkRule.measureRepeated {
            composeTestRule.setContent {
                NYContent(NUM_OF_FRAMES)
            }
        }
    }
}