package com.javatesting.messenger.console;

import com.javatesting.messenger.EmailTextGenerator;
import com.javatesting.messenger.template.TemplateAttributeEnum;

import java.util.Map;

public class WriteOutputEmailTextToConsole {
    private final EmailTextGenerator emailTextGenerator = new EmailTextGenerator();
    private final Map<TemplateAttributeEnum, String> listOfInputAttributes;

    public WriteOutputEmailTextToConsole(Map<TemplateAttributeEnum, String> listOfInputAttributes) {
        this.listOfInputAttributes = listOfInputAttributes;
    }

    public String getEmailTextOutputToConsole(Map<TemplateAttributeEnum, String> listOfInputAttributes) {
        return new String();
    }

    public String getEmailSubjectOutputToConsole(Map<TemplateAttributeEnum, String> listOfInputAttributes) {
        return new String();
    }

    public void printGeneratedEmailToConsole(Map<TemplateAttributeEnum, String> listOfInputAttributes){
    }
}
