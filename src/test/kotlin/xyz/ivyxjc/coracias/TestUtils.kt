package xyz.ivyxjc.coracias

import org.apache.commons.lang3.RandomStringUtils
import xyz.ivyxjc.coracias.models.DataEntity
import java.lang.reflect.Field
import java.time.LocalDate
import java.time.LocalDateTime
import javax.persistence.Column
import kotlin.math.abs
import kotlin.random.Random

fun compareTwoList(fs1: List<Field>, fs2: List<Field>): Boolean {
    if (fs1.size != fs2.size) {
        return false
    }
    fs1.forEach {
        if (!fs2.contains(it)) {
            return false
        }
    }
    fs2.forEach {
        if (!fs1.contains(it)) {
            return false
        }
    }
    return true
}

fun fieldAndColumnInSameOrder(fs1: List<Field>, columnNames: List<String>): Boolean {
    if (fs1.size != columnNames.size) {
        return false
    } else {
        fs1.forEachIndexed { index, it ->
            val col = it.getAnnotation(Column::class.java)
            var flag = false
            if (col != null) {
                flag = col.name == columnNames[index]
            }
            if (!flag) {
                flag = it.name == columnNames[index]
            }
            if (!flag) {
                return false
            }

        }
        return true
    }
}

fun buildRandomEntity(): DataEntity {
    val res = DataEntity()
    res.guid = abs(Random.nextLong())
    res.key = RandomStringUtils.random(8)
    res.value = RandomStringUtils.random(1000)
    res.tradeDate = LocalDateTime.now()
    res.cancelDate = LocalDate.now()
    res.officeCode = RandomStringUtils.random(20)
    return res
}

