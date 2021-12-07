// FILE: main.kt
import org.jetbrains.kotlin.fir.plugin.fsi.InjectByFqn

@InjectByFqn(name = "p", fqn = "a.s")
@InjectByFqn(name = "r", fqn = "b.s")
fun foo(): String {
    return p + r
}

fun box(): String {
    if (foo() != "strAstrB") return "Error"
    return "OK"
}

// FILE: a.kt
package a

val s: String = "strA"

// FILE: b.kt
package b

val s: String = "strB"