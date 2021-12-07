import org.jetbrains.kotlin.fir.plugin.fsi.InjectVal
import org.jetbrains.kotlin.fir.plugin.fsi.InjectedValType

@InjectVal(type = InjectedValType.String, name = "s", value = "strA")
fun foo(): String {
    return s
}

@InjectVal(type = InjectedValType.String, name = "s", value = "strB")
fun bar(): String {
    return s
}

fun box(): String {
    if (foo() != "strA") return "Error"
    if (bar() != "strB") return "Error"
    return "OK"
}