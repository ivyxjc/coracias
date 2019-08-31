package xyz.ivyxjc.coracias.convertors

import xyz.ivyxjc.coracias.model.CoraciasExportModel
import xyz.ivyxjc.coracias.strategy.CoraciasInstructions


interface CoraciasExport {
    fun performExport(
        model: CoraciasExportModel,
        instructions: CoraciasInstructions
    ): String
}

