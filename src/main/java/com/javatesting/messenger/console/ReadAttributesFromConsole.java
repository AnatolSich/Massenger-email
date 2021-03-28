package com.javatesting.messenger.console;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;



public class ReadAttributesFromConsole {
    private  BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public BufferedReader getReader() {
        return reader;
    }


    public final List<String> getFilteredInputFromConsole() {
        System.out.println("Enter required attributes for email generation (email subject, receiver's name, sender's name and sender's position):");
        List<String> listOfAttributesFromConsole = Arrays.asList(new String[4]);
        return listOfAttributesFromConsole;
    }
}


