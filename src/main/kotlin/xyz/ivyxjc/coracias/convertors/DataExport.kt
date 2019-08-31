package xyz.ivyxjc.coracias.convertors

import xyz.ivyxjc.coracias.model.TableExportModel
import xyz.ivyxjc.coracias.strategy.CoraciasFormatter
import xyz.ivyxjc.coracias.strategy.ExportInstructions


interface DataExport {
    fun performConvert(
        model: TableExportModel,
        instructions: ExportInstructions,
        formatter: CoraciasFormatter
    ): String
}

