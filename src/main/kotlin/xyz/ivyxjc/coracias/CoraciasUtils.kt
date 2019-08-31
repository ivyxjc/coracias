@file:JvmName("CoraciasUtils")

package xyz.ivyxjc.coracias

import java.lang.reflect.Field
import javax.persistence.Column
import javax.persistence.Entity

/**
 * check whether one java class [clazz] has the annotation typed [Entity] or not
 */
fun isJsr338Entity(clazz: Class<*>): Boolean {
    val annotations = clazz.annotations
    annotations.forEach {
        if (it.annotationClass.java == Entity::class.java) {
            return true
        }
    }
    return false
}

/**
 * get all fields that have the annotation [annClz] in class [dataClz]
 */
fun getFieldsByAnnotation(dataClz: Class<*>, annClz: Class<out Annotation>): List<Field> {
    val res = mutableListOf<Field>()
    val fields = dataClz.declaredFields
    fields.forEach { f ->
        f.annotations.forEach { a ->
            if (a.annotationClass.java == annClz) {
                res.add(f)
            }
        }
    }
    return res
}

/**
 * Sort the fields by its name based on the [columnNames].
 * If the name of the field is not in [columnNames], the field will not be in the result.
 * If the name in [columnNames] does not match any field's name, coracias will throw one [RuntimeException]
 */
fun sortAndFilterFieldsOrderByColumnNames(fields: List<Field>, columnNames: List<String>): List<Field> {
    val res = mutableListOf<Field>()
    val nameFieldMap = mutableMapOf<String, Field>()
    fields.forEach {
        nameFieldMap[it.name] = it
    }
    columnNames.forEach {
        when {
            nameFieldMap.containsKey(it) -> res.add(nameFieldMap[it]!!)
            else -> throw RuntimeException("The column name $it does not in Entity'name of Field's name")
        }
    }
    return res
}

/**
 * Sort the fields by its name or column's name based on the [columnNames].
 * If the name and column's name of the field is not in [columnNames], the field will not be in the result.
 * If the name in [columnNames] does not match any field's name and any column's name,
 * coracias will throw one [RuntimeException]
 *
 * Note:
 *  If the class has the annotation [Entity], The [Column]'s name has greater priority than
 *  field's name
 *  It means if the one field <fa> and the other field <fb> meet fa's Column's name== fb.field's name
 *  The result will contains fa other than fb unless ba also has annotation Column, and the column's name
 *  is also in the [columnNames]
 *  @param annFields all fields that have annotation [Column]
 *  @see Column
 */
fun sortAndFilterFieldsOrderByColumnNames(
    annFields: List<Field>,
    fields: List<Field>,
    columnNames: List<String>
): List<Field> {
    annFields.forEach {
        if (it.getAnnotation(Column::class.java) == null) {
            throw RuntimeException("all fields in parameter annFields should have the annotation ${Column::class.java.simpleName}. Field  $it violates the rule")
        }
    }
    val res = mutableListOf<Field>()
    val annNameFieldMap = mutableMapOf<String, Field>()
    val nameFieldMap = mutableMapOf<String, Field>()
    annFields.forEach {
        val ann = it.getAnnotation(Column::class.java)
        annNameFieldMap[ann.name] = it
    }
    fields.forEach {
        nameFieldMap[it.name] = it
    }
    columnNames.forEach {
        when {
            annNameFieldMap.containsKey(it) -> res.add(annNameFieldMap[it]!!)
            nameFieldMap.containsKey(it) -> res.add(nameFieldMap[it]!!)
            else -> throw RuntimeException("The column name $it does not in Entity'name of Field's name")
        }
    }
    return res
}


/**
 * get fields based on [columnNames]
 *
 * @return The first is the fields. The second is resColumnNames
 * if [columnNames] is not null or empty, the resColumnNames will be the same as [columnNames]
 *
 * Note:
 *  If the class has the annotation [Entity], The [Column]'s name has greater priority than
 *  field's name
 *  @see sortAndFilterFieldsOrderByColumnNames
 */
fun getCoraciasFields(clazz: Class<*>, columnNames: List<String>?): Pair<List<Field>, List<String>> {
    val isEntity = isJsr338Entity(clazz)
    val annFields: List<Field>
    val fields = clazz.declaredFields.toList()
    return if (isEntity) {
        annFields = getFieldsByAnnotation(clazz, Column::class.java)
        if (columnNames == null || columnNames.isEmpty()) {
            val tmpColumnNames = mutableListOf<String>()
            annFields.forEach {
                val tmpAnn = it.getAnnotation(Column::class.java)
                tmpColumnNames.add(tmpAnn.name)
            }
            Pair(annFields, tmpColumnNames)
        } else {
            Pair(sortAndFilterFieldsOrderByColumnNames(annFields, fields, columnNames), columnNames)
        }
    } else {
        if (columnNames == null || columnNames.isEmpty()) {
            val tmpColumnNames = mutableListOf<String>()
            fields.forEach {
                tmpColumnNames.add(it.name)
            }
            Pair(fields, tmpColumnNames)
        } else {
            Pair(sortAndFilterFieldsOrderByColumnNames(fields, columnNames), columnNames)
        }
    }
}
