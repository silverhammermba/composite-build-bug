package com.foobar.subbuild

import kotlin.test.Test
import kotlin.test.assertEquals

class SubBuildTest {
    @Test
    fun subBuild() {
        assertEquals("sub-build!", SubBuild.foo())
    }
}
