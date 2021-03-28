package com.javatesting.messenger.template;

public class EmailTextTemplate {

    public static final String EMAIL_SUBJECT_TEMPLATE =
            "The ${email.subject} needs review";

    public static final String EMAIL_TEXT_TEMPLATE =
            "Dear ${receiver.name}" + "\n" +
            "Kindly ask you to to take a look on the issue ${email.subject}. \n" +
             "We would like to hear yor opinion regarding this matter. " +
             "Thank you in advance \n" +
              " \n" +
              "Kind regards \n" +
              "${sender.name} \n" +
              "${sender.position} ";
}
