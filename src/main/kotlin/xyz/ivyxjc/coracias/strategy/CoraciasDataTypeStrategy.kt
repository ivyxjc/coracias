package xyz.ivyxjc.coracias.strategy

import xyz.ivyxjc.coracias.GenericDataType
import java.time.temporal.TemporalAccessor
import java.util.*

interface CoraciasDataTypeStrategy {
    fun generateType(clz: Class<out Any>): GenericDataType
}

class DefaultCoraciasDataTypeStrategy : CoraciasDataTypeStrategy {

    override fun generateType(clz: Class<out Any>): GenericDataType {
        return when {
            TemporalAccessor::class.java.isAssignableFrom(clz) -> GenericDataType.DATETIME
            Number::class.java.isAssignableFrom(clz) -> GenericDataType.NUMBER
            Int::class.java.isAssignableFrom(clz) -> GenericDataType.NUMBER
            Long::class.java.isAssignableFrom(clz) -> GenericDataType.NUMBER
            Float::class.java.isAssignableFrom(clz) -> GenericDataType.NUMBER
            Double::class.java.isAssignableFrom(clz) -> GenericDataType.NUMBER
            Date::class.java.isAssignableFrom(clz) -> GenericDataType.DATETIME
            Boolean::class.java.isAssignableFrom(clz) -> GenericDataType.BOOLEAN
            else -> GenericDataType.LITERAL
        }
    }
}
