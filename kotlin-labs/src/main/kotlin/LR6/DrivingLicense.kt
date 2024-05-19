package LR6

import java.time.LocalDate
import java.time.format.DateTimeFormatter

class DrivingLicense(
    val series: String,
    val number: String,
    val issueDate: LocalDate,
    val expirationDate: LocalDate,
) : Comparable<DrivingLicense> {

    companion object {
        private val seriesPattern = Regex("^\\d{4}$")
        private val numberPattern = Regex("^\\d{6}$")
    }

    init {
        validateSeries(series)
        validateNumber(number)
    }

    override fun toString(): String {
        val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
        return "DrivingLicense(series='$series', number='$number', issueDate=${issueDate.format(formatter)}, expirationDate=${
            expirationDate.format(
                formatter
            )
        })"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as DrivingLicense

        if (series != other.series) return false
        if (number != other.number) return false

        return true
    }

    override fun hashCode(): Int {
        var result = series.hashCode()
        result = 31 * result + number.hashCode()
        return result
    }

    override fun compareTo(other: DrivingLicense): Int {
        val seriesNumberComparison = compareValuesBy(this, other, { it.series }, { it.number })
        if (seriesNumberComparison != 0) return seriesNumberComparison

        val issueDateComparison = issueDate.compareTo(other.issueDate)
        if (issueDateComparison != 0) return issueDateComparison

        return expirationDate.compareTo(other.expirationDate)
    }

    private fun validateSeries(series: String) {
        if (!seriesPattern.matches(series)) {
            throw IllegalArgumentException("Неверная серия документа: $series")
        }
    }

    private fun validateNumber(number: String) {
        if (!numberPattern.matches(number)) {
            throw IllegalArgumentException("Неверный номер документа: $number")
        }
    }
}