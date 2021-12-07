import org.jetbrains.kotlin.fir.plugin.fsi.WithHello

@WithHello
fun foo(): String {
    return HELLO
}

fun box(): String {
    if (foo() != "Hello!") return "Error"
    return "OK"
}