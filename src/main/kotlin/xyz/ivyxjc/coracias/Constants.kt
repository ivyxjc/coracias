package xyz.ivyxjc.coracias

internal class Constants {
    companion object {
        @JvmStatic
        val TABLE_CONTAINER = """
        <table border="1" cellspacing="0" cellpadding="2">
            ${'$'}{html_data}
        </table>
    """.trimIndent()
    }
}

