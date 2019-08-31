package xyz.ivyxjc.coracias

import org.junit.Test
import xyz.ivyxjc.coracias.models.DataEntity
import xyz.ivyxjc.coracias.strategy.DefaultCoraciasFormatter

class CoraciasTest {

    @Test
    fun testCoracias() {
        val list = List(20) {
            buildRandomEntity()
        }
        val coracias = Coracias.Builder<DataEntity>()
            .coraciasFormatter(DefaultCoraciasFormatter())
            .createHeader(true)
            .build()
        println(coracias.format(list, "table_name", null))

    }

}