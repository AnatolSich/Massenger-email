package com.javatesting.messenger.console;

import com.javatesting.messenger.template.TemplateAttributeEnum;
import io.cucumber.messages.internal.com.google.common.collect.ImmutableMap;
import lombok.Getter;
import lombok.Setter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
@Setter
public class ReadAttributesFromConsole {
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

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
            System.out.println(String.format(
                    "%s incorrect input. There should be 4 input attributes in #{} provided: email subject, receiver name, sender name, sender position",
                    listOfAttributesFromConsole.toString()));
            iOException.printStackTrace();
        }

        System.out.println("listOfAttributesFromConsole: " + listOfAttributesFromConsole);
        return listOfAttributesFromConsole;
    }


    public final Map<TemplateAttributeEnum, String> createMapOfInputData(List<String> listOfAttributes) {
        return ImmutableMap.<TemplateAttributeEnum, String>builder()
                .put(TemplateAttributeEnum.EMAIL_SUBJECT, listOfAttributes.get(0))
                .put(TemplateAttributeEnum.RECEIVER_NAME, listOfAttributes.get(1))
                .put(TemplateAttributeEnum.SENDER_NAME, listOfAttributes.get(2))
                .put(TemplateAttributeEnum.SENDER_POSITION, listOfAttributes.get(3))
                .build();
    }
}


