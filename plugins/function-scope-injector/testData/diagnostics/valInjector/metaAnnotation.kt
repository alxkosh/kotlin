import org.jetbrains.kotlin.fir.plugin.fsi.InjectVal
import org.jetbrains.kotlin.fir.plugin.fsi.InjectedValType

fun println(s: String) {}

@InjectVal(type = InjectedValType.String, name = "s", value = "str")
annotation class S

@S
fun foo() {
    println(s)
}

fun bar() {
    println(<!UNRESOLVED_REFERENCE!>s<!>)
}