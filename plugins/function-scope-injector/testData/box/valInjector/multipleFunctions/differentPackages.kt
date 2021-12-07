// FILE: main.kt
fun box(): String {
    if (a.foo(1) != "strA1a") return "Error"
    if (b.foo(2) != "strB2b") return "Error"
    if (a.bar(1) != 3) return "Error"
    if (b.bar(2) != 6) return "Error"
    return "OK"
}

// FILE: a.kt
package a

import org.jetbrains.kotlin.fir.plugin.fsi.InjectVal
import org.jetbrains.kotlin.fir.plugin.fsi.InjectedValType

@InjectVal(type = InjectedValType.String, name = "s", value = "strA")
fun foo(i: Int): String {
    return s + i + "a"
}

@InjectVal(type = InjectedValType.Int, name = "v", value = "1")
fun bar(i: Int): Int {
    return v + i + 1
}

// FILE: b.kt
package b

import org.jetbrains.kotlin.fir.plugin.fsi.InjectVal
import org.jetbrains.kotlin.fir.plugin.fsi.InjectedValType

@InjectVal(type = InjectedValType.String, name = "s", value = "strB")
fun foo(i: Int): String {
    return s + i + "b"
}

@InjectVal(type = InjectedValType.Int, name = "v", value = "2")
fun bar(i: Int): Int {
    return v + i + 2
}