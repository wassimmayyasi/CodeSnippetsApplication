package models;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Snippet {
    private String name;
    private String description;
    private String programmingLanguage;
    private String path;
    private String date;
    private List<String> tags;
    private Boolean isFavourite;

    public Snippet(String name, String description,
                   String programmingLanguage, List<String> tags) {
        this.name = name;
        this.description = description;
        this.programmingLanguage = programmingLanguage;
        this.tags = tags;
        path = SnippetIO.generateValidPath();
        isFavourite = false;
        date = LocalDateTime.now().
                format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }


    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getProgrammingLanguage() {
        return programmingLanguage;
    }

    public String getDate() {
        return date;
    }

    public List<String> getTags() {
        return new ArrayList<>(tags);
    }

    String getPath() {
        return path;
    }

    Boolean getFavourite() {
        return isFavourite;
    }

    public String getCode() throws IOException {
        return new SnippetIO().read(this.getPath());
    }

    void seIsFavourite(Boolean favourite) {
        isFavourite = favourite;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setProgrammingLanguage(String programmingLanguage){
            this.programmingLanguage = programmingLanguage;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }



}
