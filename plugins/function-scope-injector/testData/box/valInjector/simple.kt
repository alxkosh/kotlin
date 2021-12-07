import org.jetbrains.kotlin.fir.plugin.fsi.InjectVal
import org.jetbrains.kotlin.fir.plugin.fsi.InjectedValType

@InjectVal(type = InjectedValType.String, name = "s", value = "str")
fun foo(): String {
    return s
}

fun box(): String {
    if (foo() != "str") return "Error"
    return "OK"
}