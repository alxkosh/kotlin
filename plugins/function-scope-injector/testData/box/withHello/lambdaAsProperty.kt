import org.jetbrains.kotlin.fir.plugin.fsi.WithHello

val foo = @WithHello { HELLO }

fun box(): String {
    if (foo() != "Hello!") return "Error"
    return "OK"
}