package com.javatesting.messenger.files;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;


public class ReadAttributesFromFile {
    public List<String> getListOfAttributesFromFile(String inputFileName) {
        List<String> listOfAttributesFromFile = new ArrayList<>();
        try (Stream<String> stream = Files.lines(Paths.get(inputFileName))) {
            stream
                    .filter(line -> line != null && line.length() > 0)
                    .map(line -> line.split("\\s+"))
                    .forEach(word -> {
                        for (String str : word)
                            if (str.contains("#{")) {
                                String filteredWord = str.substring(str.indexOf("{"), str.indexOf("}")).replace("{", "");
                                listOfAttributesFromFile.add(filteredWord);
                            }
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listOfAttributesFromFile;
    }
}

