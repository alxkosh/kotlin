// IGNORE_BACKEND_FIR: JVM_IR

import org.jetbrains.kotlin.fir.plugin.fsi.WithHello

fun foo(): String {
    @WithHello
    fun bar(): String {
        return HELLO
    }

    return bar()
}

fun box(): String {
    if (foo() != "Hello!") return "Error"
    return "OK"
}