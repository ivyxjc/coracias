package xyz.ivyxjc.coracias.convertors

import xyz.ivyxjc.coracias.Constants
import xyz.ivyxjc.coracias.CoraciasFormatter
import xyz.ivyxjc.coracias.model.TableExportModel

class HtmlDataExport : DataExport {

    override fun performConvert(
        model: TableExportModel,
        instructions: ExportInstructions,
        formatter: CoraciasFormatter
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
                objStr = formatter.format(obj)
                val isNoWrap = if (objStr == null) false else objStr.length < 100

                sb.append(" <td")
                if (isNoWrap) {
                    sb.append(" nowrap")
                }
                sb.append(">")
                sb.append(objStr)
                sb.append("</td>\n")
            }

            sb.append("</tr>\n")
        }
        return Constants.TABLE_CONTAINER.replace("\${html_data}", sb.toString())
    }
}