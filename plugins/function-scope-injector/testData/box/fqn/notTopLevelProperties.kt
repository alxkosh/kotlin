// IGNORE_BACKEND_FIR: JVM_IR
// FILE: main.kt
import org.jetbrains.kotlin.fir.plugin.fsi.InjectByFqn

@InjectByFqn(name = "p", fqn = "a.A.a")
@InjectByFqn(name = "r", fqn = "a.B.b")
fun foo(): String {
    return p + r
}

fun box(): String {
    if (foo() != "strAStrB") return "Error"
    return "OK"
}

// FILE: a.kt
package a

object A {
    const val a = "strA"
}

class B {
    companion object {
        const val b = "strB"
    }
}
