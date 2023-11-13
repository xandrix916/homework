package edu.hw5;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Problem1 {
    public static final String TIMES_PATTERN = "\\d{4}-\\d{2}-\\d{2}, \\d{2}:\\d{2} - "
        + "\\d{4}-\\d{2}-\\d{2}, \\d{2}:\\d{2}";

    private final static int OFFSET = 5;

    private int getIndexTimePart(DateTimePart dateTimePart, boolean isEnd) {
        return dateTimePart.ordinal() + (isEnd ? OFFSET : 0);
    }

    private LocalDateTime formDateTime(List<Integer> intList, boolean isEnd) {
        return LocalDateTime.of(LocalDate.of(intList.get(getIndexTimePart(DateTimePart.YEAR, isEnd)),
                intList.get(getIndexTimePart(DateTimePart.MONTH, isEnd)),
                intList.get(getIndexTimePart(DateTimePart.DAY, isEnd))),
            LocalTime.of(intList.get(getIndexTimePart(DateTimePart.HOUR, isEnd)),
                intList.get(getIndexTimePart(DateTimePart.MINUTE, isEnd))));
    }

    private Duration getVisitDuration(String startAndEndingTime) {

        Pattern pattern = Pattern.compile(TIMES_PATTERN);
        Matcher matcher = pattern.matcher(startAndEndingTime);
        if (matcher.matches()) {
            List<Integer> intList = Arrays.stream(startAndEndingTime.split("-|:|, | - "))
                .map(Integer::parseInt)
                .toList();
            LocalDateTime start = formDateTime(intList, false);
            LocalDateTime end = formDateTime(intList, true);
            return Duration.between(start, end);
        }
        return null;
    }

    public String getMediumDuration(List<String> startsAndEndings) {
        List<Duration> durations = new ArrayList<>();
        startsAndEndings.forEach(string -> durations.add(getVisitDuration(string)));
        var filteredDurations = durations.stream().filter(Objects::nonNull).toList();
        int hours = filteredDurations.stream().mapToInt(Duration::toHoursPart).sum() / durations.size();
        int minutes = filteredDurations.stream().mapToInt(Duration::toMinutesPart).sum() / durations.size();
        return String.format("%d hours %d minutes", hours, minutes);
    }
}
