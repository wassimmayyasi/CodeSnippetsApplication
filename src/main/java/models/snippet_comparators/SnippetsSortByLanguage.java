package models.snippet_comparators;

import models.Snippet;

import java.util.Comparator;

public class SnippetsSortByLanguage implements Comparator<Snippet> {

    @Override
    public int compare(Snippet o1, Snippet o2) {
        String language1 = o1.getProgrammingLanguage();
        String language2 = o2.getProgrammingLanguage();

        return language1.compareToIgnoreCase(language2);
    }
}