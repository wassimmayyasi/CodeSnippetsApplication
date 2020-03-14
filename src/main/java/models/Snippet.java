package models;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Snippet {
    private static final String CONTENT_FOLDER = "snippet_content_files";

    private String name, description, contentFilePath, programmingLanguage;
    private List<String> tags;
    private Boolean isFavourite;
    private LocalDateTime date;

    public Snippet(String name, String description, String programmingLanguage, List<String> tags) {
        this.name = name;
        this.description = description;
        this.programmingLanguage = programmingLanguage;
        this.tags = tags;

        isFavourite = false;
        date = LocalDateTime.now();
        contentFilePath = generateContentFileName();
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

    public LocalDateTime getDate() {
        return date;
    }

    public List<String> getTags() {
        return new ArrayList<>(tags);
    }

    public String getContent() throws IOException {
        StringBuilder codeStr = new StringBuilder();
        FileReader fileReader = new FileReader(new File(contentFilePath));
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        int readChar = 0;
        while((readChar = bufferedReader.read()) != -1){
            codeStr.append((char) readChar);
        }

        return codeStr.toString();
    }


    public void writeContentFile(String codeString){
        File folder = new File(CONTENT_FOLDER);
        if (!folder.exists()) {
            folder.mkdir();
        }

        try (FileWriter file = new FileWriter(contentFilePath)) {
            file.write(codeString);
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private String generateContentFileName() {
        String filePath = CONTENT_FOLDER+ "/" + name + ".txt";

        int count = 1;
        while(new File(filePath).exists()){
            filePath = CONTENT_FOLDER + "/" + name +"(" + count + ")" + ".txt";
            count++;
        }

        return filePath;
    }


}
