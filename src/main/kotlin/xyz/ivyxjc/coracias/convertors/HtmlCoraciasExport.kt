package xyz.ivyxjc.coracias.convertors

import xyz.ivyxjc.coracias.Constants
import xyz.ivyxjc.coracias.model.CoraciasExportModel
import xyz.ivyxjc.coracias.strategy.CoraciasInstructions

class HtmlCoraciasExport : CoraciasExport {

    override fun performExport(
        model: CoraciasExportModel,
        instructions: CoraciasInstructions
    ): String {
        val sb = StringBuilder()
        sb.append("<tr>\n")
        if (instructions.createHeader) {
            for (columnIndex in 0 until model.getColumnCount()) {
                val columnName = model.getColumnName(columnIndex)
                sb.append("<td><b>").append(columnName).append("</b></td>\n")
            }
        }
        sb.append("</tr>\n")

        for (rowIndex in 0 until model.getRowCount()) {
            sb.append("<tr>\n")
            for (columnIndex in 0 until model.getColumnCount()) {
                var objStr: String? = null
                val obj = model.getValue(rowIndex, columnIndex)
                val dataType = model.getGenericType(rowIndex, columnIndex)
                objStr = instructions.formatter.format(obj)
                val isNoWrap = if (objStr == null) false else objStr.length < 100

                sb.append("<td")
                if (isNoWrap) {
                    sb.append(" nowrap")
                }
                sb.append(" class=\"${dataType.value}\"")
                sb.append(">")
                sb.append(objStr)
                sb.append("</td>\n")
            }

            sb.append("</tr>\n")
        }
        return Constants.TABLE_CONTAINER.replace("\${html_data}", sb.toString())
    }
}