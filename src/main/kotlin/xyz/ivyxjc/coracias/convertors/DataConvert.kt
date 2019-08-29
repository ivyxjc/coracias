package xyz.ivyxjc.coracias.convertors

import xyz.ivyxjc.coracias.CoraciasFormatter
import xyz.ivyxjc.coracias.model.TableConvertModel


interface DataConvert {
    fun performConvert(
        model: TableConvertModel,
        instructions: ExportInstructions,
        formatter: CoraciasFormatter
    ): String
}

class ExportInstructions {
    var createHeader = true
}