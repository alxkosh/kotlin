fun fooWithHello(): String {
    return HELLO
}

fun box(): String {
    if (fooWithHello() != "Hello!") return "Error"
    return "OK"
}