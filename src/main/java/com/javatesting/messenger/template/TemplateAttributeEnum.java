package com.javatesting.messenger.template;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TemplateAttributeEnum {
        EMAIL_SUBJECT("subject"),
        RECEIVER_NAME("receiver"),
        SENDER_POSITION("sender_position"),
        SENDER_NAME("sender_name");

        private final String name;
}




