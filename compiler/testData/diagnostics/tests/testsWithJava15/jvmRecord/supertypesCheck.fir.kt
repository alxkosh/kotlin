// !API_VERSION: 1.5
// !LANGUAGE: +JvmRecordSupport
// JVM_TARGET: 15
// ENABLE_JVM_PREVIEW

abstract class Abstract
interface I

@JvmRecord
data class <!JVM_RECORD_EXTENDS_CLASS!>A1<!>(val x: String) : Abstract(), I

@JvmRecord
data class <!JVM_RECORD_EXTENDS_CLASS!>A2<!>(val x: String) : Any(), I

@JvmRecord
data class <!JVM_RECORD_EXTENDS_CLASS!>A3<!>(val x: String) : Record(), I

@JvmRecord
data class <!JVM_RECORD_EXTENDS_CLASS!>A4<!>(val x: String) : java.lang.Record(), I

@JvmRecord
data class A5(val x: String) : I

data class A6(val x: String) : Record(), I

data class A7(val x: String) : java.lang.Record(), I
