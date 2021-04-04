package com.javatesting.messenger;

import com.javatesting.messenger.console.ReadAttributesFromConsole;
import com.javatesting.messenger.console.WriteOutputEmailTextToConsole;
import com.javatesting.messenger.files.ReadAttributesFromFile;
import com.javatesting.messenger.files.WriteOutputTextToFile;
import com.javatesting.messenger.template.TemplateAttributeEnum;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Properties;


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
        } catch (java.io.FileNotFoundException e) {
            System.err.println("Can't find file.properties");
        } catch (IOException e) {
            System.err.println("I/O failed.");
        }
        Map<TemplateAttributeEnum, String> listOfInputAttributes;

        if (System.getProperty("file.name") == null
                || System.getProperty("file.attributes") == null
                || System.getProperty("file.name").isEmpty()
                || System.getProperty("file.attributes").isEmpty()) {
            ReadAttributesFromConsole readAttributesFromConsoleMode = new ReadAttributesFromConsole();
            EmailTextGenerator emailTextGenerator = new EmailTextGenerator();
            listOfInputAttributes = emailTextGenerator.createMapOfInputData(readAttributesFromConsoleMode.getFilteredInputFromConsole());
            WriteOutputEmailTextToConsole writeOutputEmailTextToConsole = new WriteOutputEmailTextToConsole(listOfInputAttributes);
            writeOutputEmailTextToConsole.printGeneratedEmailToConsole(listOfInputAttributes);
        } else {
            ReadAttributesFromFile readAttributesFromFileMode = new ReadAttributesFromFile();
            List<String> filteredList = readAttributesFromFileMode.getListOfAttributesFromFile(System.getProperty("file.attributes"));
            EmailTextGenerator emailTextGenerator = new EmailTextGenerator();
            listOfInputAttributes = emailTextGenerator.createMapOfInputData(filteredList);
            WriteOutputTextToFile writeGeneratedEmailToFile = new WriteOutputTextToFile();
            writeGeneratedEmailToFile.writeGeneratedEmailToFile(listOfInputAttributes, System.getProperty("file.name"));
        }
    }
}
