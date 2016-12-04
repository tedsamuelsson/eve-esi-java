package org.devfleet.esi.api;

public interface Mailbox {
    MailboxKey getKey();

    Long getLabelId();

    String getLabel();

    Integer getColor();

    Integer getUnread();
}
