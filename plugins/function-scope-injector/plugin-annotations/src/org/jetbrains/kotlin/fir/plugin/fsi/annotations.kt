package org.jetbrains.kotlin.fir.plugin.fsi

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.SOURCE)
annotation class WithHello

@Target(AnnotationTarget.FUNCTION, AnnotationTarget.ANNOTATION_CLASS)
@Retention(AnnotationRetention.SOURCE)
@Repeatable
annotation class InjectByFqn(val name: String, val fqn: String)

@Target(AnnotationTarget.FUNCTION, AnnotationTarget.ANNOTATION_CLASS)
@Retention(AnnotationRetention.SOURCE)
@Repeatable
annotation class InjectVal(val type: InjectedValType, val name: String, val value: String)

enum class InjectedValType {
    Boolean,
    Byte,
    Short,
    Int,
    Long,
    Float,
    Double,
    Char,
    String
}