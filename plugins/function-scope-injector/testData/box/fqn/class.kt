// FILE: main.kt
import org.jetbrains.kotlin.fir.plugin.fsi.InjectByFqn

@InjectByFqn(name = "B", fqn = "a.A")
fun foo(): String {
    val b = B()
    return b.a + b.foo()
}

fun box(): String {
    if (foo() != "astr") return "Error"
    return "OK"
}

// FILE: a.kt
package a

class A {
    val a = "a"

    fun foo(): String {
        return "str"
    }
}