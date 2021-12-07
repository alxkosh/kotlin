import org.jetbrains.kotlin.fir.plugin.fsi.InjectVal
import org.jetbrains.kotlin.fir.plugin.fsi.InjectedValType

@InjectVal(type = InjectedValType.Boolean, name = "bool", value = "false")
@InjectVal(type = InjectedValType.Byte, name = "b", value = "0")
@InjectVal(type = InjectedValType.Short, name = "s", value = "1")
@InjectVal(type = InjectedValType.Int, name = "i", value = "2")
@InjectVal(type = InjectedValType.Long, name = "l", value = "-3")
@InjectVal(type = InjectedValType.Float, name = "f", value = "4.0")
@InjectVal(type = InjectedValType.Double, name = "d", value = "5.0")
@InjectVal(type = InjectedValType.Char, name = "c", value = "c")
@InjectVal(type = InjectedValType.String, name = "str", value = "str")
fun foo(): String {
    if (!bool) {
        val r = b + s + i + l + f + d
        return str + c + r
    } else {
        return "Error"
    }
}

fun box(): String {
    if (foo() != "strc9.0") return "Error"
    return "OK"
}