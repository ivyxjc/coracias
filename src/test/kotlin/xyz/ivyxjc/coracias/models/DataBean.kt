package xyz.ivyxjc.coracias.models

import java.time.LocalDate
import java.time.LocalDateTime

class DataBean {
    var guid: Long = Long.MIN_VALUE

    lateinit var key: String

    lateinit var value: String

    lateinit var tradeDate: LocalDateTime

    lateinit var cancelDate: LocalDate

    lateinit var officeCode: String

}

