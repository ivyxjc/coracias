package xyz.ivyxjc.coracias

import java.lang.reflect.Field
import javax.persistence.Column

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