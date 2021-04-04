package com.javatesting.messenger;

import com.javatesting.messenger.files.ReadAttributesFromFile;
import com.javatesting.messenger.files.WriteOutputTextToFile;
import com.javatesting.messenger.template.TemplateAttributeEnum;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.*;

import static com.javatesting.messenger.template.TemplateAttributeEnum.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.spy;


public class FileValidInputTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    private final ReadAttributesFromFile fileReader = spy(new ReadAttributesFromFile());
    private final EmailTextGenerator emailTextGenerator = spy(new EmailTextGenerator());


    @Test
    public void testValidInputFromFileAndFiltering() {
        final ReadAttributesFromFile fileReader = new ReadAttributesFromFile();
        List<String> testListOfAttributes = fileReader.getListOfAttributesFromFile("attributes.txt");
        List<String> expectedListOfAttributes = new ArrayList<>(Arrays.asList("TestSubject", "TestReceiverName", "TestSenderName", "TestSenderPosition"));
        Assert.assertEquals(expectedListOfAttributes, testListOfAttributes);
    }

    @Test
    @DisplayName("Test of ConsoleMode with valid input using Mockito")
    @Tag("smoke")
    public void testCreateMapOfInputDataFile() {
        final List<String> expectedListOfAttributes = new ArrayList<>(Arrays.asList("TestSubject", "TestReceiverName", "TestSenderName", "TestSenderPosition"));
        final Map<TemplateAttributeEnum, String> expectedMap = new HashMap<>() {
            {
                put(EMAIL_SUBJECT, "TestSubject");
                put(RECEIVER_NAME, "TestReceiverName");
                put(SENDER_NAME, "TestSenderName");
                put(SENDER_POSITION, "TestSenderPosition");
            }
        };
        final Map<TemplateAttributeEnum, String> inputMap = emailTextGenerator.createMapOfInputData(expectedListOfAttributes);
        Assert.assertTrue(expectedMap.entrySet().stream()
                .allMatch(e -> e.getValue().equals(inputMap.get(e.getKey()))));
    }


    @Test
    @DisplayName("Test of FileMode with valid input using Mockito")
    @Tag("smoke")
    public void testValidInputGetFilteredInputFromFile() {
        final List<String> expectedListOfAttributes = new ArrayList<>(Arrays.asList("TestSubject", "TestReceiverName", "TestSenderName", "TestSenderPosition"));

        Properties properties = new Properties();
        properties.setProperty("file.name", "output.txt");
        properties.setProperty("file.attributes", "attributes.txt");
        System.setProperties(properties);
        final String expectedEmailText = "The TestSubject needs review \n" +
                " Dear TestReceiverName\n" +
                "Kindly ask you to to take a look on the issue TestSubject. \n" +
                "We would like to hear yor opinion regarding this matter. " +
                "Thank you in advance \n" +
                " \n" +
                "Kind regards \n" +
                "TestSenderName \n" +
                "TestSenderPosition ";
        WriteOutputTextToFile writeOutputTextToFile = new WriteOutputTextToFile();
        Map<TemplateAttributeEnum, String> mapOfInputData = emailTextGenerator.createMapOfInputData(expectedListOfAttributes);
        assertEquals(expectedEmailText, String.format("%s \n %s",
                writeOutputTextToFile.getEmailSubjectOutputToFile(mapOfInputData),
                writeOutputTextToFile.getEmailTextOutputToFile(mapOfInputData)));
    }
}


