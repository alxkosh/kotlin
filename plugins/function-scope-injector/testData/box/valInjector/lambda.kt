import org.jetbrains.kotlin.fir.plugin.fsi.InjectVal
import org.jetbrains.kotlin.fir.plugin.fsi.InjectedValType

val l = @InjectVal(type = InjectedValType.String, name = "s", value = "str") { s }

fun foo(f: () -> String = @InjectVal(type = InjectedValType.String, name = "s", value = "str") { s }): String {
    return f()
}

/*fun foo(): String {
    return (@InjectVal(type = InjectedValType.String, name = "s", value = "str") { s })()
}*/

fun box(): String {
    if (l() != "str") return "Error"
    if (foo() != "str") return "Error"
    return "OK"
}