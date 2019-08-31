package xyz.ivyxjc.coracias

import org.junit.Assert
import org.junit.Test
import xyz.ivyxjc.coracias.models.DataEntity
import javax.persistence.Column

class CoraciasUtilsTest {
    @Test
    fun testIsJsr338Entity() {
        Assert.assertTrue(isJsr338Entity(DataEntity::class.java))
    }

    @Test
    fun testGetFieldsByAnnotation() {
        val fields = getFieldsByAnnotation(DataEntity::class.java, Column::class.java)
        println(fields)
    }


    @Test
    fun testGetCoraciasFields() {
        val fields = getCoraciasFields(DataEntity::class.java, null)
        Assert.assertFalse(compareTwoList(fields, DataEntity::class.java.declaredFields.toList()))
    }

    @Test
    fun testGetCoraciasFields2() {
        val columnNames = mutableListOf<String>()
        columnNames.add("GUID")
        columnNames.add("tradeDate")
        val fields = getCoraciasFields(DataEntity::class.java, columnNames)
        Assert.assertTrue(fields[0].name == "guid")
        Assert.assertTrue(fields[1].name == "tradeDate")
    }

    @Test
    fun testGetCoraciasFields3() {
        val columnNames = mutableListOf<String>()
        columnNames.add("KEY")
        columnNames.add("officeCode")
        columnNames.add("GUID")
        columnNames.add("VALUE")
        columnNames.add("tradeDate")
        val fields = getCoraciasFields(DataEntity::class.java, columnNames)
        Assert.assertTrue(fieldAndColumnInSameOrder(fields, columnNames))
    }
}



