package xyz.ivyxjc.coracias.models

import java.time.LocalDate
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.Entity


@Entity
class DataEntity {
    @Column(name = "GUID")
    var guid: Long = Long.MIN_VALUE

    @Column(name = "KEY")
    lateinit var key: String

    @Column(name = "VALUE")
    lateinit var value: String

    @Column(name = "TRADE_DATE")
    lateinit var tradeDate: LocalDateTime

    @Column(name = "CANCEL_DATE")
    lateinit var cancelDate: LocalDate

    lateinit var officeCode: String

}
