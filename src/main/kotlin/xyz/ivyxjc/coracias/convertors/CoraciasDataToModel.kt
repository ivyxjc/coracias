package xyz.ivyxjc.coracias.convertors

import xyz.ivyxjc.coracias.GenericDataType
import xyz.ivyxjc.coracias.getCoraciasFields
import xyz.ivyxjc.coracias.model.CoraciasExportModel
import xyz.ivyxjc.coracias.model.DefaultCoraciasExportModel
import xyz.ivyxjc.coracias.strategy.CoraciasInstructions
import java.lang.reflect.Field

/**
 * Convert data into [CoraciasExportModel].
 * @see CoraciasInstructions
 * @see CoraciasDataTypeStrategy
 */
interface CoraciasDataToModel<T : Any> {
    fun performConvert(list: List<T>, name: String, instructions: CoraciasInstructions): CoraciasExportModel
    fun performConvert(
        list: List<T>,
        name: String,
        columnNames: List<String>?,
        instructions: CoraciasInstructions
    ): CoraciasExportModel
}

class DefaultCoraciasDataToModel<T : Any> : CoraciasDataToModel<T> {

    override fun performConvert(list: List<T>, name: String, instructions: CoraciasInstructions): CoraciasExportModel {
        return performConvert(list, name, null, instructions)
    }

    override fun performConvert(
        list: List<T>,
        name: String,
        columnNames: List<String>?,
        instructions: CoraciasInstructions
    ): CoraciasExportModel {
        if (list.isEmpty()) {
            throw RuntimeException("DataToModel's source list should not be empty")
        }
        val dataClz = list[0]::class.java
        val fieldsAndColumnNamesPair = getCoraciasFields(dataClz, columnNames)
        val fields = fieldsAndColumnNamesPair.first
        //if columnNames is not null or empty, calculateColumnNames is equals to columnNames
        val calculateColumnNames = fieldsAndColumnNamesPair.second

        val model = DefaultCoraciasExportModel(list.size, fields.size)
        model.setTableName(name)
        model.setColumnNames(calculateColumnNames.toTypedArray())

        list.forEach {
            val pair = buildRowData(it, fields, instructions)
            model.addRow(pair)
        }
        return model
    }

    private fun buildRowData(
        data: T,
        fields: List<Field>,
        instructions: CoraciasInstructions
    ): Pair<Array<Any?>, Array<GenericDataType>> {

        val a1 = Array<Any?>(fields.size) {
            null
        }
        val a2 = Array(fields.size) {
            GenericDataType.LITERAL
        }
        for (i in 0 until fields.size) {
            val f = fields[i]
            f.isAccessible = true
            a1[i] = f.get(data)
            a2[i] = instructions.coraciasDataTypeStrategy.generateType(f.type)
        }
        return Pair(a1, a2)
    }

}