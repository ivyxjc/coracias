package xyz.ivyxjc.coracias

import xyz.ivyxjc.coracias.convertors.CoraciasDataToModel
import xyz.ivyxjc.coracias.convertors.CoraciasExport
import xyz.ivyxjc.coracias.convertors.DefaultCoraciasDataToModel
import xyz.ivyxjc.coracias.convertors.HtmlCoraciasExport
import xyz.ivyxjc.coracias.strategy.*

class Coracias<T : Any>(private val builder: Builder<T>) {

    fun format(data: List<T>, tableName: String): String {
        return format(data, tableName, null)
    }

    fun format(data: List<T>, tableName: String, columnNames: List<String>?): String {
        builder.exportInstructions.coraciasDataTypeStrategy = builder.coraciasDataTypeStrategy
        builder.exportInstructions.formatter = builder.coraciasFormatter
        return builder.coraciasExport.performExport(
            builder.coraciasDataToModel.performConvert(
                data,
                tableName,
                columnNames,
                builder.exportInstructions
            ), builder.exportInstructions
        )
    }


    class Builder<T : Any> {
        internal var coraciasDataTypeStrategy: CoraciasDataTypeStrategy = DefaultCoraciasDataTypeStrategy()
        internal var coraciasFormatter: CoraciasFormatter = DefaultCoraciasFormatter()
        internal var coraciasDataToModel: CoraciasDataToModel<T>
        internal var coraciasExport: CoraciasExport = HtmlCoraciasExport()
        var exportInstructions = CoraciasInstructions()

        init {
            coraciasDataToModel = DefaultCoraciasDataToModel()
        }

        fun coraciasTypeStrategy(coraciasDataTypeStrategy: CoraciasDataTypeStrategy) = apply {
            this.coraciasDataTypeStrategy = coraciasDataTypeStrategy
        }

        fun dataToModel(coraciasDataToModel: CoraciasDataToModel<T>) {
            this.coraciasDataToModel = coraciasDataToModel
        }

        fun coraciasFormatter(formatter: CoraciasFormatter) = apply {
            this.coraciasFormatter = formatter
        }

        fun createHeader(bool: Boolean) = apply {
            exportInstructions.createHeader = bool
        }

        fun build(): Coracias<T> {
            return Coracias(this)
        }
    }


}