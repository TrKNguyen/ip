package nguyen;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task that has a start time and an end time.
 */
public class Event extends Task {
    protected LocalDate from, to;
    /**
     * Constructs a new Event task with the given description, start time, and end time.
     *
     * @param description The description of the event.
     * @param from The start time of the event.
     * @param to The end time of the event.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = DateParser.parseDate(from);
        this.to = DateParser.parseDate(to);
    }
    /**
     * Returns a string representation of the event task.
     *
     * @return A formatted string representing the event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " to: " + to.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
