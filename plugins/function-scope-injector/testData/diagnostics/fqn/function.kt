// FILE: main.kt
import org.jetbrains.kotlin.fir.plugin.fsi.InjectByFqn

fun println(s: String) {}

@InjectByFqn(name = "p", fqn = "a.s")
fun foo() {
    println(p())
    println(<!UNRESOLVED_REFERENCE!>s<!>())
}

fun bar() {
    println(<!UNRESOLVED_REFERENCE!>s<!>())
    println(<!UNRESOLVED_REFERENCE!>p<!>())
}

// FILE: a.kt
package a

fun s(): String {
    return "str"
}