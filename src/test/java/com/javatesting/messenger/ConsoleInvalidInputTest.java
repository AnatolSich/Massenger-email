package com.javatesting.messenger;

import com.javatesting.messenger.console.ReadAttributesFromConsole;
import org.junit.Rule;
import org.junit.Test;
import org.junit.jupiter.api.*;
import org.junit.rules.ExpectedException;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.io.BufferedReader;
import java.io.IOException;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

@DisplayName("Console Mode invalid tests")
public class ConsoleInvalidInputTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();
    public ExpectedException exceptionRule = ExpectedException.none();

    @Mock
    private BufferedReader bufferedReader = Mockito.mock(BufferedReader.class);
    private ReadAttributesFromConsole consoleReader = spy(new ReadAttributesFromConsole());


    @BeforeEach
    public void setUp() {
        doReturn(bufferedReader).when(consoleReader).getReader();
    }

    @Test
    @Tag("smoke")
    @Tag("testing of NullPointerException with Exception rule")
    public void testEmptyAttributeInputFromConsole() throws IOException {
        String testInput = "Test #{TestSubject} and and #{TestSenderName} and #{TestSenderPosition}";
        Mockito.when(bufferedReader.readLine()).thenReturn(testInput);
        exceptionRule.expect(NullPointerException.class);
    }

    @Test
    @Tag("smoke")
    @Tag("testing of IndexOutOfBoundsException with Exception rule. expected = IndexOutOfBoundsException.class doesn't work here")
    public void testMoreThanRequiredAttributesFromConsole() throws IOException {
        String testInput = "Test #{TestSubject} and  #{TestReceiverName} and #{TestSenderName} and #{TestSenderPosition} but one more #{TestRedundantat} test";
        Mockito.when(bufferedReader.readLine()).thenReturn(testInput);
        exceptionRule.expect(IndexOutOfBoundsException.class);
    }

    @AfterEach
    public void afterEach(TestInfo testInfo) { System.out.println("after each: " + testInfo.getDisplayName() + "in" + this);}

}
