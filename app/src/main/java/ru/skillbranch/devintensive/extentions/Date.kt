package ru.skillbranch.devintensive.extentions

import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit.*


const val SECOND = 1000L
const val MINUTE = 60 * SECOND
const val HOUR = 60 * MINUTE
const val DAY = 24 * HOUR


fun Date.format(pattern: String = "HH:mm:ss dd.MM.yy"): String {

    val dateFormat = SimpleDateFormat(pattern, Locale("ru"))
    return dateFormat.format(this)
}

fun Date.add(value: Int, units: TimeUnits = TimeUnits.SECOND): Date {
    val newDate = Date(this.time)

    newDate.time += when (units) {
        TimeUnits.SECOND -> value * SECOND
        TimeUnits.MINUTE -> value * MINUTE
        TimeUnits.HOUR -> value * HOUR
        TimeUnits.DAY -> value * DAY
    }

    return newDate
}

fun Date.humanizeDiff(date: Date = Date()): String {
    val diffSeconds = (this.time - date.time) / SECOND
    val isPositive = diffSeconds > 0L
    val diffSecondsAbs = Math.abs(diffSeconds)

    return when {
        diffSecondsAbs == 0L -> "только что"
        diffSecondsAbs in 1L until 45L -> if (isPositive)
            "через несколько секунд"
        else
            "несколько секунд назад"
        diffSecondsAbs in 45L until 75L -> if (isPositive)
            "через минуту"
        else
            "минуту назад"
        diffSecondsAbs in 75L until MINUTES.toSeconds(45L) -> if (isPositive)
            humanizeDiffMinutesForward(diffSecondsAbs)
        else
            humanizeDiffMinutesAgo(diffSecondsAbs)
        diffSecondsAbs in MINUTES.toSeconds(45L) until MINUTES.toSeconds(75L) -> if (isPositive)
            "через час"
        else
            "час назад"
        diffSecondsAbs in MINUTES.toSeconds(75L) until HOURS.toSeconds(22L) -> if (isPositive)
            humanizeDiffHoursForward(diffSecondsAbs)
        else
            humanizeDiffHoursAgo(diffSecondsAbs)
        diffSecondsAbs in HOURS.toSeconds(22L) until HOURS.toSeconds(26L) -> if (isPositive)
            "через день"
        else
            "день назад"
        diffSecondsAbs in HOURS.toSeconds(26L) until DAYS.toSeconds(360L) -> if (isPositive)
            humanizeDiffDaysForward(diffSecondsAbs)
        else
            humanizeDiffDaysAgo(diffSecondsAbs)
        else -> if (isPositive) "более чем через год" else "более года назад"
    }
}

private fun humanizeDiffMinutesAgo(diffSeconds: Long) = pickPluralString(
    amount = SECONDS.toMinutes(diffSeconds),
    zeroFormat = "%d минут назад",
    oneFormat = "%d минуту назад",
    fewFormat = "%d минуты назад",
    manyFormat = "%d минут назад"
)

private fun humanizeDiffHoursAgo(diffSeconds: Long) = pickPluralString(
    amount = SECONDS.toHours(diffSeconds),
    zeroFormat = "%d часов назад",
    oneFormat = "%d час назад",
    fewFormat = "%d часа назад",
    manyFormat = "%d часов назад"
)

private fun humanizeDiffDaysAgo(diffSeconds: Long) = pickPluralString(
    amount = SECONDS.toDays(diffSeconds),
    zeroFormat = "%d дней назад",
    oneFormat = "%d день назад",
    fewFormat = "%d дня назад",
    manyFormat = "%d дней назад"
)

private fun humanizeDiffMinutesForward(diffSeconds: Long) = pickPluralString(
    amount = SECONDS.toMinutes(diffSeconds),
    zeroFormat = "через %d минут",
    oneFormat = "через %d минуту",
    fewFormat = "через %d минуты",
    manyFormat = "через %d минут"
)

private fun humanizeDiffHoursForward(diffSeconds: Long) = pickPluralString(
    amount = SECONDS.toHours(diffSeconds),
    zeroFormat = "через %d часов",
    oneFormat = "через %d час",
    fewFormat = "через %d часа",
    manyFormat = "через %d часов"
)

private fun humanizeDiffDaysForward(diffSeconds: Long) = pickPluralString(
    amount = SECONDS.toDays(diffSeconds),
    zeroFormat = "через %d дней",
    oneFormat = "через %d день",
    fewFormat = "через %d дня",
    manyFormat = "через %d дней"
)

private fun pickPluralString(
    amount: Long,
    zeroFormat: String,
    oneFormat: String,
    fewFormat: String,
    manyFormat: String
) =
    when {
        amount == 0L -> zeroFormat.format(amount)
        amount % 100L in 11L..19L -> manyFormat.format(amount)
        amount % 10L == 1L -> oneFormat.format(amount)
        amount % 10L in 2L..4L -> fewFormat.format(amount)
        else -> manyFormat.format(amount)
    }


enum class TimeUnits(
    private val zeroFormat: String,
    private val oneFormat: String,
    private val fewFormat: String,
    private val manyFormat: String
) {
    SECOND(
        zeroFormat = "%d секунд",
        oneFormat = "%d секунду",
        fewFormat = "%d секунды",
        manyFormat = "%d секунд"
    ),
    MINUTE(
        zeroFormat = "%d минут",
        oneFormat = "%d минуту",
        fewFormat = "%d минуты",
        manyFormat = "%d минут"
    ),
    HOUR(
        zeroFormat = "%d часов",
        oneFormat = "%d час",
        fewFormat = "%d часа",
        manyFormat = "%d часов"
    ),
    DAY(
        zeroFormat = "%d дней",
        oneFormat = "%d день",
        fewFormat = "%d дня",
        manyFormat = "%d дней"
    );

    fun plural(value: Int) = pickPluralString(
        amount = value.toLong(),
        zeroFormat = zeroFormat,
        oneFormat = oneFormat,
        fewFormat = fewFormat,
        manyFormat = manyFormat
    )
}