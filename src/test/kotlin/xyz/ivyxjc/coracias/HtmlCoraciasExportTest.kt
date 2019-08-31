package xyz.ivyxjc.coracias

import org.junit.Test
import xyz.ivyxjc.coracias.convertors.DefaultCoraciasDataToModel
import xyz.ivyxjc.coracias.convertors.HtmlCoraciasExport
import xyz.ivyxjc.coracias.models.DataEntity
import xyz.ivyxjc.coracias.strategy.CoraciasInstructions

class HtmlCoraciasExportTest {
    @Test
    fun testExport() {
        val list = List(20) {
            buildRandomEntity()
        }
        val dateToModel = DefaultCoraciasDataToModel<DataEntity>()
        val res = dateToModel.performConvert(list, "table_name", CoraciasInstructions())
        val dataExport = HtmlCoraciasExport()
        dataExport.performExport(res, CoraciasInstructions())
    }
}
