package xyz.ivyxjc.coracias.convertors

import xyz.ivyxjc.coracias.model.CoraciasExportModel
import xyz.ivyxjc.coracias.strategy.CoraciasInstructions


/**
 * The core of Coracias.
 * Accept the model and instruction to generate the final result.
 * The model is generate by [CoraciasDataToModel]  and the instructions may be the default one or the one
 * that is customized by the developer.
 *
 * @see CoraciasDataToModel
 * @see CoraciasInstructions
 */
interface CoraciasExport {
    fun performExport(
        model: CoraciasExportModel,
        instructions: CoraciasInstructions
    ): String
}

