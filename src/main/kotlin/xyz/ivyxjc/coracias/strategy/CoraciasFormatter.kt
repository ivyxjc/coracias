package xyz.ivyxjc.coracias.strategy

import java.text.DateFormat
import java.text.DecimalFormat
import java.text.NumberFormat
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.time.temporal.TemporalAccessor
import java.time.temporal.UnsupportedTemporalTypeException
import java.util.*

interface CoraciasFormatter {
    fun format(obj: Any?): String?
}

class DefaultCoraciasFormatter : CoraciasFormatter {
    private val locale = Locale.getDefault()
    var jsr310DateTimeFormatter: DateTimeFormatter =
        DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM).withLocale(locale)

    var jsr310DateFormatter: DateTimeFormatter =
        DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM).withLocale(locale)

    var classicDateFormatter: DateFormat = DateFormat.getDateInstance(DateFormat.DEFAULT, locale)
        set(value) {
            classicDateFormatterTl = object : ThreadLocal<DateFormat>() {
                override fun initialValue(): DateFormat {
                    return classicDateFormatter.clone() as DateFormat
                }
            }
            field = value
        }
    var numberFormat: NumberFormat = DecimalFormat.getInstance(locale)

    init {
        numberFormat.isGroupingUsed = true
        numberFormat.maximumFractionDigits = 10
    }

    internal var classicDateFormatterTl = object : ThreadLocal<DateFormat>() {
        override fun initialValue(): DateFormat {
            return classicDateFormatter.clone() as DateFormat
        }
    }

    private fun formatNumber(num: Number): String {
        return numberFormat.format(num)
    }

    private fun formatJsr310DateTime(temporalAccessor: TemporalAccessor): String {
        return try {
            jsr310DateTimeFormatter.format(temporalAccessor)
        } catch (e: UnsupportedTemporalTypeException) {
            jsr310DateFormatter.format(temporalAccessor)
        }
    }

    private fun formatClassicDate(date: Date): String {
        return classicDateFormatterTl.get().format(date)
    }

    override fun format(obj: Any?): String? {
        return when (obj) {
            is Number -> formatNumber(obj)
            is TemporalAccessor -> formatJsr310DateTime(obj)
            is Date -> formatClassicDate(obj)
            is String -> obj
            else -> null
        }
    }

    fun jsr310DateTimeFormatter(dateTimeFormatter: DateTimeFormatter) = apply {
        this.jsr310DateTimeFormatter = dateTimeFormatter
    }

    fun jsr310DateFormatter(dateFormatter: DateTimeFormatter) = apply {
        this.jsr310DateFormatter = dateFormatter
    }

    fun classicDateTimeFormatter(classDateTimeFormatter: DateFormat) = apply {
        this.classicDateFormatter = classicDateFormatter
    }

    fun numberFormatter(numberFormat: NumberFormat) = apply {
        this.numberFormat = numberFormat
    }

    fun build() = apply { }

}
