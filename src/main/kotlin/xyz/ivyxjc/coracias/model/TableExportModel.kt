package xyz.ivyxjc.coracias.model

interface TableExportModel {
    fun getTableName(): String
    fun getColumnCount(): Int
    fun getRowCount(): Int
    fun getValue(rowIndex: Int, columnIndex: Int): Any?
    fun getColumnName(columnIndex: Int): String
}

interface ModelAction {
    fun addRow(row: Array<Any?>)
    fun setTableName(name: String)
    fun setColumnNames(columnNames: Array<String>)
}


/**
 * Not Thread-Safe
 */
class SimpleExportModel(private val rowNum: Int, private val columnNum: Int) : TableExportModel, ModelAction {

    private val data = Array<Array<Any?>>(rowNum) {
        Array(columnNum) {
            null
        }
    }
    private lateinit var tableName: String
    private lateinit var columnNames: Array<String>
    private var count = 0

    override fun addRow(row: Array<Any?>) {
        data[count] = row
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
}