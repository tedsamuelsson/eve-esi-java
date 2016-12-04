package org.devfleet.esi.api;

public class MailboxKey {

    private final int id;

    private MailboxKey(int id) {
        this.id = id;
    }

    public static MailboxKey mailboxKey(int id) {
        return new MailboxKey(id);
    }
}
