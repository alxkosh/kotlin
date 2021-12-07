import org.jetbrains.kotlin.fir.plugin.fsi.WithHello

@WithHello
fun foo(): String {
    val HELLO = "Hi!"
    return HELLO
}

fun box(): String {
    if (foo() != "Hi!") return "Error"
    return "OK"
}
