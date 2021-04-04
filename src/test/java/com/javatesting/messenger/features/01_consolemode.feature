Feature: Console mode testing

  //@setUpConsoleMockAndConsoleReaderSpy
  Scenario Outline: 01.01. Check valid input filtering
  Given console up and running
  When user enter '<validInput>' to filter it
  Then filtered input are the same as '<expectedInput>'
    Examples:
      |validInput                                                                                   | expectedInput                                                           |
      | Test #{TestSubject} and #{TestReceiverName} and #{TestSenderName} and #{TestSenderPosition} | "TestSubject","TestReceiverName", "TestSenderName", "TestSenderPosition"|


  Scenario Outline: 01.02. Check invalid input and corresponding exceptions
    Given console up and running
    Then user enter '<invalidInput>' and '<expectedException>' occurs
    Examples:
      |invalidInput                                                                                                                      | expectedException        |
      | Test #{TestSubject} and and #{TestSenderName} and #{TestSenderPosition}                                                          | NullPointerException     |
      | Test #{TestSubject} and  #{TestReceiverName} and #{TestSenderName} and #{TestSenderPosition} but one more #{TestRedundant} test| IndexOutOfBoundsException|


