fun fooWithHello(): String {
    return HELLO
}

fun barWithHello(): String {
    return HELLO + "!"
}

fun box(): String {
    if (fooWithHello() != "Hello!") return "Error"
    if (barWithHello() != "Hello!!") return "Error"
    return "OK"
}