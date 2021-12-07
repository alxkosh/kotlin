// FILE: main.kt
import org.jetbrains.kotlin.fir.plugin.fsi.InjectByFqn

@InjectByFqn(name = "A", fqn = "a.b.A.B.C")
fun foo(): String {
    return A().d
}

fun box(): String {
    if (foo() != "str") return "Error"
    return "OK"
}

// FILE: a.kt
package a.b

class A {
    companion object B {
        class C {
            val d = "str"
        }
    }
}