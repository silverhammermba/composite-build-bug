package com.foobar.subproject

import kotlin.test.Test
import kotlin.test.assertEquals

class SubProjectTest {
    @Test
    fun subProject() {
        assertEquals("sub-project!", SubProject.foo())
    }
}
