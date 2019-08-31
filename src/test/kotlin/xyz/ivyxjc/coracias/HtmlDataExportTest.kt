package xyz.ivyxjc.coracias

import org.junit.Test
import xyz.ivyxjc.coracias.convertors.DefaultDataToModel
import xyz.ivyxjc.coracias.convertors.ExportInstructions
import xyz.ivyxjc.coracias.convertors.HtmlDataExport
import xyz.ivyxjc.coracias.models.DataEntity

class HtmlDataExportTest {
    @Test
    fun testExport() {
        val list = List(20) {
            buildRandomEntity()
        }
        val dateToModel = DefaultDataToModel<DataEntity>()
        val res = dateToModel.performConvert(list, "table_name", ExportInstructions())
        val dataExport = HtmlDataExport()
        dataExport.performConvert(res, ExportInstructions(), DefaultCoraciasFormatter())
    }
}
