package nguyen;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class DateParser {
    private static final List<String> DATE_PATTERNS = Arrays.asList(
            "yyyy-MM-dd HHmm",
            "yyyy/MM/dd HHmm",
            "dd/MM/yyyy HHmm",
            "MM-dd-yyyy HHmm",
            "yyyy-MM-dd",
            "yyyy/MM/dd",
            "dd/MM/yyyy",
            "MM-dd-yyyy",
            "dd MMM yyyy",
            "MMM d yyyy",
            "MMM dd yyyy",
            "MMM dd, yyyy"
    );

    public static LocalDate parseDate(String input) {
        for (String pattern : DATE_PATTERNS) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern, Locale.ENGLISH);
                input = input.trim();
                LocalDate result = LocalDate.parse(input, formatter);
                if (result != null) {
                    return result;
                }
            } catch (DateTimeParseException ignored) {
            }
        }
        return null;
    }
}
