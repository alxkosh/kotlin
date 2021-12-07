// FILE: main.kt
import org.jetbrains.kotlin.fir.plugin.fsi.InjectByFqn

@InjectByFqn(name = "s", fqn = "a.s")
fun foo(): String {
    return s
}

@InjectByFqn(name = "s", fqn = "a.s")
fun bar(): String {
    return s
}

fun box(): String {
    if (foo() != "str") return "Error"
    if (bar() != "str") return "Error"
    return "OK"
}

// FILE: a.kt
package a

val s: String = "str"