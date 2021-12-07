// FILE: main.kt
import org.jetbrains.kotlin.fir.plugin.fsi.InjectByFqn

fun println(s: String) {}

@InjectByFqn(name = "B", fqn = "a.A")
fun foo() {
    val b = B()
    println(b.s)
    println(b.foo())
    println(<!UNRESOLVED_REFERENCE!>A<!>().s)
}

fun bar() {
    println(<!UNRESOLVED_REFERENCE!>A<!>().s)
    println(<!UNRESOLVED_REFERENCE!>B<!>().s)
}

// FILE: a.kt
package a

class A() {
    val s: String = "str"

    fun foo(): String {
        return "foo"
    }
}