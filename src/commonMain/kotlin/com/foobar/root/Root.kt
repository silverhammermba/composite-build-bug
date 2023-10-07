package com.foobar.root

import com.foobar.subbuild.SubBuild
import com.foobar.subproject.SubProject

object Root {
    fun foo() = SubProject.foo() + SubBuild.foo()
}
