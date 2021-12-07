// FILE: main.kt
import org.jetbrains.kotlin.fir.plugin.fsi.InjectByFqn

@InjectByFqn(name = "b", fqn = "a.bar")
fun foo(): String {
    return b()
}

fun box(): String {
    if (foo() != "str") return "Error"
    return "OK"
}

// FILE: a.kt
package a

fun bar(): String {
    return "str"
}