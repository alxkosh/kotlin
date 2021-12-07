// FILE: main.kt
import org.jetbrains.kotlin.fir.plugin.fsi.InjectByFqn

val p: String = "p"
val s: String = "s"

@InjectByFqn(name = "p", fqn = "a.s")
fun foo(): String {
    return p
}

fun box(): String {
    if (foo() != "str") return "Error"
    return "OK"
}

// FILE: a.kt
package a

val s: String = "str"