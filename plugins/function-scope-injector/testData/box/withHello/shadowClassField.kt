import org.jetbrains.kotlin.fir.plugin.fsi.WithHello

class Foo {
    val HELLO: String = "Hi"

    @WithHello
    fun foo(): String {
        return HELLO
    }
}

fun box(): String {
    if (Foo().foo() != "Hello!") return "Error"
    return "OK"
}
