package com.javatesting.messenger;

import com.javatesting.messenger.console.ReadAttributesFromConsole;
import com.javatesting.messenger.console.WriteOutputEmailTextToConsole;
import com.javatesting.messenger.template.TemplateAttributeEnum;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.gen5.api.BeforeEach;
import org.junit.gen5.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

import static com.javatesting.messenger.template.TemplateAttributeEnum.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

@DisplayName("Console Mode valid tests")
public class ConsoleValidInputTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    private BufferedReader bufferedReader = Mockito.mock(BufferedReader.class);
    private ReadAttributesFromConsole consoleReader = spy(new ReadAttributesFromConsole());

    @BeforeEach
    public void setUp() {
        doReturn(bufferedReader).when(consoleReader).getReader();
    }

    @Test
    @DisplayName("Test of ConsoleMode with valid input using Mockito")
    @Tag("smoke")
    public void testValidInputGetFilteredInputFromConsole() throws IOException {
        final List<String> expectedListOfAttributes = new ArrayList<>(Arrays.asList("TestSubject", "TestReceiverName", "TestSenderName", "TestSenderPosition"));
        final String testInput = "Test #{TestSubject} and  #{TestReceiverName} and #{TestSenderName} and #{TestSenderPosition}";
        InputStream stream = new ByteArrayInputStream((testInput + "\n").getBytes(StandardCharsets.UTF_8)); //this stream will output the testInput string
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        consoleReader.setReader(reader);
        final List<String> testInputFilteredResult = consoleReader.getFilteredInputFromConsole();
        assertEquals(expectedListOfAttributes, testInputFilteredResult);
    }

    @Test
    @DisplayName("Test of ConsoleMode with valid input using Mockito")
    @Tag("smoke")
    public void testCreateMapOfInputData() {
        final List<String> expectedListOfAttributes = new ArrayList<>(Arrays.asList("TestSubject", "TestReceiverName", "TestSenderName", "TestSenderPosition"));
        final Map<TemplateAttributeEnum, String> expectedMap = new HashMap<>() {
            {
                put(EMAIL_SUBJECT, "TestSubject");
                put(RECEIVER_NAME, "TestReceiverName");
                put(SENDER_NAME, "TestSenderName");
                put(SENDER_POSITION, "TestSenderPosition");
            }
        };
        final Map<TemplateAttributeEnum, String> inputMap = consoleReader.createMapOfInputData(expectedListOfAttributes);
        Assert.assertTrue(expectedMap.entrySet().stream()
                .allMatch(e -> e.getValue().equals(inputMap.get(e.getKey()))));
    }

    @Test
    @Disabled("Disabled until issue with writer to map is fixed")
    public void testValidInputFromConsoleAndOutPut() throws IOException {
        final List<String> expectedListOfAttributes = new ArrayList<>(Arrays.asList("TestSubject", "TestReceiverName", "TestSenderName", "TestSenderPosition"));
        final Map<TemplateAttributeEnum, String> listOfAttributes = consoleReader.createMapOfInputData(expectedListOfAttributes);
        final String expectedEmailText = "The TestSubject needs review \n" +
                " Dear TestReceiverName\n" +
                "Kindly ask you to to take a look on the issue TestSubject. \n" +
                "We would like to hear yor opinion regarding this matter. " +
                "Thank you in advance \n" +
                " \n" +
                "Kind regards \n" +
                "TestSenderName \n" +
                "TestSenderPosition ";

        final WriteOutputEmailTextToConsole writeOutputEmailTextToConsole = new WriteOutputEmailTextToConsole(listOfAttributes);
        final String generatedEmailText = String.format(
                "%s \n %s", writeOutputEmailTextToConsole.getEmailSubjectOutputToConsole(listOfAttributes),
                writeOutputEmailTextToConsole.getEmailTextOutputToConsole(listOfAttributes));
        assertEquals(expectedEmailText, generatedEmailText);
    }
}
