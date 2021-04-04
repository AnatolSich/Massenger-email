package com.javatesting.messenger;

import com.javatesting.messenger.template.EmailTextTemplate;
import com.javatesting.messenger.template.TemplateAttributeEnum;
import io.cucumber.messages.internal.com.google.common.collect.ImmutableMap;
import org.apache.commons.lang3.text.StrSubstitutor;

import java.util.List;
import java.util.Map;

import static com.javatesting.messenger.template.TemplateAttributeEnum.*;

public class EmailTextGenerator {

    public Map<String, String> getEmailTextMapper(Map<TemplateAttributeEnum, String> listOfAttributes) {
        return ImmutableMap.<String, String>builder()
                .put("email.subject", listOfAttributes.get(EMAIL_SUBJECT))
                .put("receiver.name", listOfAttributes.get(RECEIVER_NAME))
                .put("sender.name", listOfAttributes.get(SENDER_NAME))
                .put("sender.position", listOfAttributes.get(SENDER_POSITION))
                .build();
    }

    public String getEmailText(Map<String, String> mapper) {
        StrSubstitutor sub = new StrSubstitutor(mapper);
        String emailTextString = sub.replace(EmailTextTemplate.EMAIL_TEXT_TEMPLATE);
        System.out.println("emailTextString = " + emailTextString);
        return emailTextString;
    }

    public static String getEmailSubject(Map<String, String> mapper) {
        StrSubstitutor sub = new StrSubstitutor(mapper);
        String emailSubjectString = sub.replace(EmailTextTemplate.EMAIL_SUBJECT_TEMPLATE);
        System.out.println("emailSubjectString = " + emailSubjectString);
        return emailSubjectString;
    }

    public  Map<TemplateAttributeEnum, String> createMapOfInputData(List<String> listOfAttributes) {
        return ImmutableMap.<TemplateAttributeEnum, String>builder()
                .put(TemplateAttributeEnum.EMAIL_SUBJECT, listOfAttributes.get(0))
                .put(TemplateAttributeEnum.RECEIVER_NAME, listOfAttributes.get(1))
                .put(TemplateAttributeEnum.SENDER_NAME, listOfAttributes.get(2))
                .put(TemplateAttributeEnum.SENDER_POSITION, listOfAttributes.get(3))
                .build();
    }
}
