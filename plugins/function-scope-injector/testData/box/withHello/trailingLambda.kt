// IGNORE_BACKEND_FIR: JVM_IR

import org.jetbrains.kotlin.fir.plugin.fsi.WithHello

fun bar(f: () -> String): String {
    return f() + "!"
}

fun foo(): String {
    return bar @WithHello { HELLO }
}

fun box(): String {
    if (foo() != "Hello!!") return "Error"
    return "OK"
}