package xyz.ivyxjc.coracias

import org.junit.Test
import xyz.ivyxjc.coracias.models.DataEntity
import xyz.ivyxjc.coracias.strategy.DefaultCoraciasDataTypeStrategy
import xyz.ivyxjc.coracias.strategy.DefaultCoraciasFormatter
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter

class CoraciasTest {

    @Test
    fun testCoracias() {
        val list = List(20) {
            buildRandomEntity()
        }
        val coracias = Coracias.Builder<DataEntity>()
            .coraciasFormatter(
                DefaultCoraciasFormatter()
                    .jsr310DateTimeFormatter(DateTimeFormatter.ofPattern("yyyy-MM-dd hh.MM.ss.SSS"))
                    .jsr310DateFormatter(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
                    .classicDateTimeFormatter(SimpleDateFormat("yyyy-MM-dd hh:MM:ss"))
                    .build()
            )
            .coraciasTypeStrategy(DefaultCoraciasDataTypeStrategy())
            .createHeader(true)
            .build()
        println(coracias.format(list, "table_name", null))

    }

}