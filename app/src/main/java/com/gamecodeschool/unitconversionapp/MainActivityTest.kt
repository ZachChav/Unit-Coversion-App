package com.gamecodeschool.unitconversionapp

import org.junit.Assert.assertEquals
import org.junit.Test

class MainActivityTest {

    @Test
    fun testMeterToFootConversion() {
        val activity = MainActivity()
        assertEquals(3.28084, activity.convertMeter(1.0, 1), 0.001)
    }

    @Test
    fun testMeterToInchConversion() {
        val activity = MainActivity()
        assertEquals(39.3701, activity.convertMeter(1.0, 2), 0.001)
    }

    // Add more tests for other conversions and edge cases
}
