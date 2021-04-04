package com.javatesting.messenger.console;

import lombok.Getter;
import lombok.Setter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class ReadAttributesFromConsole {
    private  BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public BufferedReader getReader() {
        return reader;
    }

    public final List<String> getFilteredInputFromConsole() {
        System.out.println("Enter required attributes for email generation (email subject, receiver's name, sender's name and sender's position):");
        List<String> listOfAttributesFromConsole = Arrays.asList(new String[4]);
        try {
            listOfAttributesFromConsole = reader.readLine()
                    .lines().distinct()
                    .filter(line -> line != null && line.length() > 0)
                    .map(line -> line.split("\\s+")).flatMap(Arrays::stream)
                    .filter(str -> str.contains("#{")).map(str -> str.substring(str.indexOf("{"), str.indexOf("}"))
                            .replace("{", ""))
                    .collect(Collectors.toList());
        } catch (IOException | IndexOutOfBoundsException iOException) {
            System.out.println(String.format("%s incorrect input. There should be 4 input attributes in #{} provided: email subject, receiver name, sender name, sender position", listOfAttributesFromConsole.toString()));
            iOException.printStackTrace();
        }
        return listOfAttributesFromConsole;
    }

}


