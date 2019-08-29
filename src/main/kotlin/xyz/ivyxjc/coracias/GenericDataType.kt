package xyz.ivyxjc.coracias

@Deprecated("deprecated")
enum class GenericDataType(val value: String) {

    LITERAL("Literal"),
    INTEGER("Integer"),
    DECIMAL("Decimal"),
    JSR310_DATETIME("Jsr310Datetime"),
    JSR310_DATE("Jsr310Date"),
    CLASSIC_DATE("ClassicDate"),
    BOOLEAN("Boolean")
}
