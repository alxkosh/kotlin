fun foo(): String {
    fun barWithHello(): String {
        return HELLO
    }

    return barWithHello()
}

fun box(): String {
    if (foo() != "Hello!") return "Error"
    return "OK"
}