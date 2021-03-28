package com.javatesting.messenger;


import java.io.FileInputStream;
import java.io.IOException;
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
            System.out.println(System.getProperty("file.name").length());
        } catch (java.io.FileNotFoundException e) {
            System.err.println("Can't find file.properties");
        } catch (IOException e) {
            System.err.println("I/O failed.");
        }

    }
}
