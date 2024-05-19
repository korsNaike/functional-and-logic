package javalr6;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;

public class DrivingLicense implements Comparable<DrivingLicense> {
    private static final String SERIES_PATTERN = "^\\d{4}$";
    private static final String NUMBER_PATTERN = "^\\d{6}$";

    private final String series;
    private final String number;
    private final LocalDate issueDate;
    private final LocalDate expirationDate;

    public DrivingLicense(String series, String number, LocalDate issueDate, LocalDate expirationDate) {
        this.series = validateSeries(series);
        this.number = validateNumber(number);
        this.issueDate = issueDate;
        this.expirationDate = expirationDate;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return "DrivingLicense(series='" + series + "', number='" + number + "', issueDate=" + issueDate.format(formatter) + ", expirationDate=" + expirationDate.format(formatter) + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DrivingLicense that = (DrivingLicense) o;
        return series.equals(that.series) && number.equals(that.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(series, number);
    }

    @Override
    public int compareTo(DrivingLicense other) {
        return compareValuesBy(this, other,
                DrivingLicense::getSeries,
                DrivingLicense::getNumber,
                DrivingLicense::getIssueDate,
                DrivingLicense::getExpirationDate);
    }

    private String validateSeries(String series) {
        if (!series.matches(SERIES_PATTERN)) {
            throw new IllegalArgumentException("Неверная серия документа: " + series);
        }
        return series;
    }

    private String validateNumber(String number) {
        if (!number.matches(NUMBER_PATTERN)) {
            throw new IllegalArgumentException("Неверный номер документа: " + number);
        }
        return number;
    }

    public String getSeries() {
        return series;
    }

    public String getNumber() {
        return number;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    private static <T> int compareValuesBy(T obj1, T obj2, Function<T, ?>... getters) {
        return compareValuesBy(obj1, obj2, 0, getters);
    }

    private static <T> int compareValuesBy(T obj1, T obj2, int index, Function<T, ?>[] getters) {
        if (index >= getters.length) {
            return 0;
        }

        Function<T, ?> getter = getters[index];
        Object val1 = getter.apply(obj1);
        Object val2 = getter.apply(obj2);

        int comparison = compareValues(val1, val2);
        if (comparison != 0) {
            return comparison;
        }

        return compareValuesBy(obj1, obj2, index + 1, getters);
    }

    private static int compareValues(Object val1, Object val2) {
        if (val1 == null && val2 == null) {
            return 0;
        } else if (val1 == null) {
            return -1;
        } else if (val2 == null) {
            return 1;
        } else if (val1 instanceof Comparable<?> && val2 instanceof Comparable<?>) {
            Comparable c1 = (Comparable<?>) val1;
            Comparable c2 = (Comparable<?>) val2;
            return c1.compareTo(c2);
        } else {
            return val1.toString().compareTo(val2.toString());
        }
    }
}

