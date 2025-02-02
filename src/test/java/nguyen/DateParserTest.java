package nguyen;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class DateParserTest {

    @Test
    void testValidDates() {
        assertEquals(LocalDate.of(2024, 1, 1), DateParser.parseDate("2024-01-01"));
        assertEquals(LocalDate.of(2024, 1, 1), DateParser.parseDate("2024/01/01"));
        assertEquals(LocalDate.of(2024, 1, 1), DateParser.parseDate("01/01/2024"));
        assertEquals(LocalDate.of(2024, 1, 1), DateParser.parseDate("01-01-2024"));
        assertEquals(LocalDate.of(2024, 2, 1), DateParser.parseDate("1 Feb 2024"));
        assertEquals(LocalDate.of(2024, 2, 1), DateParser.parseDate("Feb 1 2024"));
        assertEquals(LocalDate.of(2024, 2, 1), DateParser.parseDate("Feb 01 2024"));
        assertEquals(LocalDate.of(2024, 2, 1), DateParser.parseDate("Feb 01, 2024"));
        assertEquals(LocalDate.of(2024, 3, 1), DateParser.parseDate("2024-03-01"));
        assertEquals(LocalDate.of(2024, 3, 1), DateParser.parseDate("2024/03/01"));
        assertEquals(LocalDate.of(2024, 3, 1), DateParser.parseDate("01/03/2024"));
        assertEquals(LocalDate.of(2024, 3, 1), DateParser.parseDate("03-01-2024"));
    }

    @Test
    void testInvalidDates() {
        assertNull(DateParser.parseDate("2024-13-01"));
        assertNull(DateParser.parseDate("Hello World"));
        assertNull(DateParser.parseDate(""));
        assertNull(DateParser.parseDate("2024.02.01"));
        assertNull(DateParser.parseDate("2/31"));
    }
}
