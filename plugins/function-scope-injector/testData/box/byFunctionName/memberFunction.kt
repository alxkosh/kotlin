class Foo {
    fun fooWithHello(): String {
        return HELLO
    }
}

fun box(): String {
    if (Foo().fooWithHello() != "Hello!") return "Error"
    return "OK"
}