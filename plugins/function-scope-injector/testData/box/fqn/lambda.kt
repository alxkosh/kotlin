// FILE: main.kt
import org.jetbrains.kotlin.fir.plugin.fsi.InjectByFqn

val l = @InjectByFqn(name = "p", fqn = "a.s") { p }

fun foo(f: () -> String = @InjectByFqn(name = "p", fqn = "a.s") { p }): String {
    return f()
}

/*fun foo(): String {
    return (@InjectByFqn(name = "p", fqn = "a.s") { p })()
}*/

fun box(): String {
    if (l() != "str") return "Error"
    if (foo() != "str") return "Error"
    return "OK"
}

// FILE: a.kt
package a

val s: String = "str"