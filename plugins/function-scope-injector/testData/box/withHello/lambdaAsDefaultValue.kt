import org.jetbrains.kotlin.fir.plugin.fsi.WithHello

fun foo(f: () -> String = @WithHello { HELLO }): String {
    return f() + "!"
}

fun box(): String {
    if (foo() != "Hello!!") return "Error"
    return "OK"
}