import org.jetbrains.kotlin.fir.plugin.fsi.WithHello

@WithHello
fun foo(): () -> String {
    fun bar(): String {
        return HELLO
    }
    return {
        bar() + HELLO
    }
}

fun box(): String {
    if (foo()() != "Hello!Hello!") return "Error"
    return "OK"
}