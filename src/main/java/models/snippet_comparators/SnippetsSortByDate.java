package models.snippet_comparators;

import models.Snippet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;

public class SnippetsSortByDate implements Comparator<Snippet> {

    @Override
    public int compare(Snippet o1, Snippet o2) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        LocalDateTime date1 = LocalDateTime.parse(o1.getDate(), formatter);
        LocalDateTime date2 = LocalDateTime.parse(o2.getDate(), formatter);

        return date1.compareTo(date2);
    }
}