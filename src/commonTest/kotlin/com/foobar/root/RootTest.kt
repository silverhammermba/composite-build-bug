package com.foobar.root

import kotlin.test.Test
import kotlin.test.assertEquals

class RootTest {
    @Test
    fun root() {
        assertEquals("sub-project!sub-build!", Root.foo())
    }
}
