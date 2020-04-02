package models.snippet_comparators;

import models.Snippet;

import java.util.Comparator;

public class SnippetsSortByName implements Comparator<Snippet> {

    @Override
    public int compare(Snippet o1, Snippet o2) {
        String name1 = o1.getName();
        String name2 = o2.getName();

        return name1.compareToIgnoreCase(name2);
    }
}