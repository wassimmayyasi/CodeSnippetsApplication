package models;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.io.*;
import java.util.Random;

// TODO: mention this entire class is package private and thus can only be used by the models,
//  so controllers cannot directly access or modify files
class SnippetIO {

    private static final String FOLDER = "snippet_files";
    static final String JSON_FILE_PATH = FOLDER + "/snippet.json";


    static  {
        try {
            initializeFolder();
            initializeJsonFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static void initializeFolder() {
        File folder = new File(FOLDER);
        if (!folder.exists()) {
            folder.mkdir();
        }
    }


    private static void initializeJsonFile() throws IOException {
        File snippetFile = new File(JSON_FILE_PATH);

        if(snippetFile.createNewFile()){
            JSONObject emptyObj = new JSONObject();
            emptyObj.put("snippets", new JSONArray());
            write(JSON_FILE_PATH, emptyObj.toJSONString());
        }
    }


    static String generateValidPath() {
        Random random = new Random();
        String path = FOLDER + "/" + random.nextInt(Integer.MAX_VALUE) + ".txt";

        if(new File(path).exists()) {
            generateValidPath();
        }

        return path;
    }


    static void write(String path, String content){
        try (FileWriter fileWriter = new FileWriter(path)) {
            fileWriter.write(content);
            fileWriter.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    static String read(String path) throws IOException {
        File file = new File(path);

        if(!file.exists()) {
            return "";
        }

        try (FileReader fileReader = new FileReader(file)) {
            StringBuilder content = new StringBuilder();
            int readChar;

            while((readChar = fileReader.read()) != -1) {
                content.append((char) readChar);
            }

            return content.toString();
        }
    }


    static void deleteFile(String path) {
        new File(path).delete();
    }
}
