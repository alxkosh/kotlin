fun println(s: String) {}

fun fooWithHello() {
    println(HELLO)
}

fun bar() {
    println(<!UNRESOLVED_REFERENCE!>HELLO<!>)
}