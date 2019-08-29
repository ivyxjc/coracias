package xyz.ivyxjc.coracias

import org.apache.commons.lang3.RandomStringUtils
import xyz.ivyxjc.coracias.convertors.ExportInstructions
import xyz.ivyxjc.coracias.convertors.HtmlDataConvert
import xyz.ivyxjc.coracias.model.SimpleConvertModel
import xyz.ivyxjc.coracias.models.DataEntity
import java.time.LocalDateTime
import kotlin.math.abs
import kotlin.random.Random

fun buildRandomEntity(): DataEntity {
    val res = DataEntity()
    res.guid = abs(Random.nextLong())
    res.key = RandomStringUtils.random(8)
    res.value = RandomStringUtils.random(8)
    res.tradeDate = LocalDateTime.now()
    return res
}

fun toModel(entity: DataEntity): Array<Any?> {
    val arr = Array<Any?>(4) {
        null
    }
    arr[0] = entity.guid
    arr[1] = entity.key
    arr[2] = entity.value
    arr[3] = entity.tradeDate
    return arr
}

fun main() {
    val list = List(20) {
        buildRandomEntity()
    }
    val model = SimpleConvertModel(20, 4)
    list.forEachIndexed { index, _ ->
        model.addRow(toModel(list[index]))
    }
    val columnNames = arrayOf("guid", "key", "value", "tradeDate")
    val convert = HtmlDataConvert()
    model.setColumnNames(columnNames)
    model.setTableName("table")
    val res = convert.performConvert(model, ExportInstructions(), DefaultCoraciasFormatter())

    println(res)
}