package com.javatesting.messenger;

import com.javatesting.messenger.console.ReadAttributesFromConsole;
import com.javatesting.messenger.console.WriteOutputEmailTextToConsole;
import com.javatesting.messenger.template.TemplateAttributeEnum;
import lombok.Getter;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;


@Getter
class MainApp {
    public static void main(String[] args) throws IOException {

        try {
            // set up new properties object from file "/resources/file.properties"
            FileInputStream propFile = new FileInputStream("src/main/resources/file.properties");
            Properties p = new Properties(System.getProperties());
            p.load(propFile);

            // set the system properties
            System.setProperties(p);
            System.getProperties().list(System.out);    // display new properties
            System.out.println(System.getProperty("file.name").length());
        } catch (java.io.FileNotFoundException e) {
            System.err.println("Can't find file.properties");
        } catch (java.io.IOException e) {
            System.err.println("I/O failed.");
        }

        Map<TemplateAttributeEnum, String> listOfInputAttributes;
        ReadAttributesFromConsole readAttributesFromConsoleMode = new ReadAttributesFromConsole();
        listOfInputAttributes = readAttributesFromConsoleMode.createMapOfInputData(readAttributesFromConsoleMode.getFilteredInputFromConsole());
        WriteOutputEmailTextToConsole writeOutputEmailTextToConsole = new WriteOutputEmailTextToConsole(listOfInputAttributes);
        writeOutputEmailTextToConsole.printGeneratedEmailToConsole(listOfInputAttributes);

    }
}
