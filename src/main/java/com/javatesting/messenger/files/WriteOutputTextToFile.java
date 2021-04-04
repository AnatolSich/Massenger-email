package com.javatesting.messenger.files;

import com.javatesting.messenger.EmailTextGenerator;
import com.javatesting.messenger.template.TemplateAttributeEnum;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class WriteOutputTextToFile {
    private final EmailTextGenerator emailTextGenerator = new EmailTextGenerator();

    public String getEmailTextOutputToFile(Map<TemplateAttributeEnum, String> listOfInputAttributes) {
        return emailTextGenerator.getEmailText(emailTextGenerator.getEmailTextMapper(listOfInputAttributes));
    }

    public String getEmailSubjectOutputToFile(Map<TemplateAttributeEnum, String> listOfInputAttributes) {
        return emailTextGenerator.getEmailSubject(emailTextGenerator.getEmailTextMapper(listOfInputAttributes));
    }

    public void writeGeneratedEmailToFile(Map<TemplateAttributeEnum, String> listOfInputAttributes, String fileName) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
        writer.write(String.format("%s \n %s", getEmailSubjectOutputToFile(listOfInputAttributes), getEmailTextOutputToFile(listOfInputAttributes)));
        writer.close();
    }
}
