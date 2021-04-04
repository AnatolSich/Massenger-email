package com.javatesting.messenger.console;

import com.javatesting.messenger.EmailTextGenerator;
import com.javatesting.messenger.template.TemplateAttributeEnum;

import java.util.Map;

public class WriteOutputEmailTextToConsole {
  //  private final ReadAttributesFromConsole readAttributesFromConsoleMode = new ReadAttributesFromConsole();
    private final EmailTextGenerator emailTextGenerator = new EmailTextGenerator();
    private final Map<TemplateAttributeEnum, String> listOfInputAttributes;// = readAttributesFromConsoleMode.createMapOfInputData(readAttributesFromConsoleMode.getFilteredInputFromConsole());

    public WriteOutputEmailTextToConsole(Map<TemplateAttributeEnum, String> listOfInputAttributes) {
        this.listOfInputAttributes = listOfInputAttributes;
    }


    public String getEmailTextOutputToConsole(Map<TemplateAttributeEnum, String> listOfInputAttributes) {
        return emailTextGenerator.getEmailText(emailTextGenerator.getEmailTextMapper(listOfInputAttributes));
    }

    public String getEmailSubjectOutputToConsole(Map<TemplateAttributeEnum, String> listOfInputAttributes) {
        return emailTextGenerator.getEmailSubject(emailTextGenerator.getEmailTextMapper(listOfInputAttributes));
    }

    public void printGeneratedEmailToConsole(Map<TemplateAttributeEnum, String> listOfInputAttributes){
        System.out.println(String.format("%s \n %s", getEmailSubjectOutputToConsole(listOfInputAttributes), getEmailTextOutputToConsole(listOfInputAttributes)));
    }
}
