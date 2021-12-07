// FILE: main.kt
import org.jetbrains.kotlin.fir.plugin.fsi.InjectByFqn

@InjectByFqn(name = "p", fqn = "a.s")
fun foo() {
    p = "rts"
}

@InjectByFqn(name = "p", fqn = "a.s")
fun bar(): String {
    return p
}

fun box(): String {
    foo()
    if (bar() != "rts") return "Error"
    return "OK"
}

// FILE: a.kt
package a

var s: String = "str"