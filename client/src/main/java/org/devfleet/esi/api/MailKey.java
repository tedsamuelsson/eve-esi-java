package org.devfleet.esi.api;

public class MailKey {

    private final Long id;

    public MailKey(Long id) {
        this.id = id;
    }

    public static MailKey mailKey(Long id) {
        return new MailKey(id);
    }

    public Long getId() {
        return id;
    }
}
