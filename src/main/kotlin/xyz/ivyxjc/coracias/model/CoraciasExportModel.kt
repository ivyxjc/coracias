package xyz.ivyxjc.coracias.model

import xyz.ivyxjc.coracias.GenericDataType

interface CoraciasExportModel {
    fun getTableName(): String
    fun getColumnCount(): Int
    fun getRowCount(): Int
    fun getValue(rowIndex: Int, columnIndex: Int): Any?
    fun getColumnName(columnIndex: Int): String
    fun getGenericType(rowIndex: Int, columnIndex: Int): GenericDataType
}

abstract class AbstractModel : CoraciasExportModel {
    abstract fun addRow(row: Pair<Array<Any?>, Array<GenericDataType>>)
    abstract fun setTableName(name: String)
    abstract fun setColumnNames(columnNames: Array<String>)
}


/**
 * Not Thread-Safe
 */
class DefaultCoraciasExportModel(private val rowNum: Int, private val columnNum: Int) : AbstractModel() {

    private val data = Array<Array<Any?>>(rowNum) {
        Array(columnNum) {
            null
        }
    }

    private val dataTypes = Array(rowNum) {
        Array(columnNum) {
            GenericDataType.LITERAL
        }
    }
    private lateinit var tableName: String
    private lateinit var columnNames: Array<String>
    private var count = 0

    override fun addRow(rowPair: Pair<Array<Any?>, Array<GenericDataType>>) {
        data[count] = rowPair.first
        dataTypes[count] = rowPair.second
        count++
    }

    override fun setTableName(name: String) {
        this.tableName = name
    }

    override fun setColumnNames(columnNames: Array<String>) {
        if (columnNames.size != columnNum) {
            throw RuntimeException("columnNames'length is not equals to the column count")
        }
        this.columnNames = columnNames

    }

    override fun getTableName(): String {
        return tableName
    }

    override fun getColumnCount(): Int {
        return columnNum
    }

    override fun getRowCount(): Int {
        return rowNum
    }

    override fun getValue(rowIndex: Int, columnIndex: Int): Any? {
        return data[rowIndex][columnIndex]
    }

    override fun getColumnName(columnIndex: Int): String {
        return columnNames[columnIndex]
    }

    override fun getGenericType(rowIndex: Int, columnIndex: Int): GenericDataType {
        return dataTypes[rowIndex][columnIndex]
    }
}