import org.jetbrains.kotlin.fir.plugin.fsi.WithHello

fun println(s: String) {}

@WithHello
fun foo() {
    println(HELLO)
}

fun bar() {
    println(<!UNRESOLVED_REFERENCE!>HELLO<!>)
}