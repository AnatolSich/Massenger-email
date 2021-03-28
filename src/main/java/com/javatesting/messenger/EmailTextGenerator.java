package com.javatesting.messenger;

import com.javatesting.messenger.template.EmailTextTemplate;
import com.javatesting.messenger.template.TemplateAttributeEnum;
import io.cucumber.messages.internal.com.google.common.collect.ImmutableMap;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.text.StrSubstitutor;

import java.util.Map;

import static com.javatesting.messenger.template.TemplateAttributeEnum.*;


@Getter
@Setter
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
        return emailTextString;
    }

    public static String getEmailSubject(Map<String, String> mapper) {
        StrSubstitutor sub = new StrSubstitutor(mapper);
        String emailSubjectString = sub.replace(EmailTextTemplate.EMAIL_SUBJECT_TEMPLATE);
        return emailSubjectString;
    }
}
