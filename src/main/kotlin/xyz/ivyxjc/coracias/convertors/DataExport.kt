package xyz.ivyxjc.coracias.convertors

import xyz.ivyxjc.coracias.CoraciasFormatter
import xyz.ivyxjc.coracias.model.TableExportModel


interface DataExport {
    fun performConvert(
        model: TableExportModel,
        instructions: ExportInstructions,
        formatter: CoraciasFormatter
    ): String
}

class ExportInstructions {
    var createHeader = true
}