// WITH_STDLIB

import org.jetbrains.kotlin.fir.plugin.fsi.InjectVal
import org.jetbrains.kotlin.fir.plugin.fsi.InjectedValType

@InjectVal(type = InjectedValType.String, name = "s", value = "strA")
fun foo(): String {
    return s
}

@InjectVal(type = InjectedValType.String, name = "s", value = "strB")
fun foo(i: Int): String {
    return s + i
}

@InjectVal(type = InjectedValType.String, name = "s", value = "strC")
fun <T> foo(t: T): String {
    return s + t
}

@InjectVal(type = InjectedValType.String, name = "s", value = "strD")
fun <T> foo(vararg ts: T): String {
    return s + ts.joinToString(separator = "")
}

@InjectVal(type = InjectedValType.String, name = "s", value = "strE")
fun foo(f: () -> String): String {
    return s + f()
}

fun box(): String {
    if (foo() != "strA") return "Error"
    if (foo(1) != "strB1") return "Error"
    if (foo(2.0) != "strC2.0") return "Error"
    if (foo(1, 2, 3) != "strD123") return "Error"
    if (foo() { "str" } != "strEstr") return "Error"
    return "OK"
}