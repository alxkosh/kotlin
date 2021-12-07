import org.jetbrains.kotlin.fir.plugin.fsi.WithHello

@WithHello
fun foo(HELLO: String): String {
    return HELLO
}

fun box(): String {
    if (foo("Hi!") != "Hi!") return "Error"
    return "OK"
}
