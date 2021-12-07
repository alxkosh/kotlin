// FILE: main.kt
import org.jetbrains.kotlin.fir.plugin.fsi.InjectByFqn

@InjectByFqn(name = "E", fqn = "a.A")
fun foo(): String {
    return E.B.toString() + E.C
}

@InjectByFqn(name = "F", fqn = "a.A.B")
@InjectByFqn(name = "G", fqn = "a.A.C")
fun bar(): String {
    return F.toString() + G
}

fun box(): String {
    if (foo() != "strBstrC") return "Error"
    if (bar() != "strBstrC") return "Error"
    return "OK"
}

// FILE: a.kt
package a

enum class A {
    B {
        override fun toString(): String = "strB"
    },
    C {
        override fun toString(): String = "strC"
    }
}