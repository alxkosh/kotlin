// FILE: main.kt
import org.jetbrains.kotlin.fir.plugin.fsi.InjectByFqn

@InjectByFqn(name = "p", fqn = "a.s")
fun foo(): String {
    return p
}

@InjectByFqn(name = "p", fqn = "b.s")
fun bar(): String {
    return p
}

fun box(): String {
    if (foo() != "strA") return "Error"
    if (bar() != "strB") return "Error"
    return "OK"
}

// FILE: a.kt
package a

val s: String = "strA"

// FILE: b.kt
package b

val s: String = "strB"