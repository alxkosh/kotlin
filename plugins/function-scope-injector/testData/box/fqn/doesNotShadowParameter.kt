// FILE: main.kt
import org.jetbrains.kotlin.fir.plugin.fsi.InjectByFqn

@InjectByFqn(name = "p", fqn = "a.s")
fun foo(p: String): String {
    return p
}

fun box(): String {
    if (foo("rts") != "rts") return "Error"
    return "OK"
}

// FILE: a.kt
package a

val s: String = "str"