package org.devfleet.esi.api;

import org.devfleet.esi.impl.MailData;

import java.util.List;
import java.util.Map;

public interface Mail {
    public static class Recipient {
        private Long id;
        private String type;

        public Long getId() {
            return id;
        }

        public Recipient setId(Long id) {
            this.id = id;
            return this;
        }

        public String getType() {
            return type;
        }

        public Recipient setType(String type) {
            this.type = type;
            return this;
        }
    }

    Map<LabelKey, Label> getLabels();

    List<MailData.Recipient> getRecipients();

    MailKey getKey();

    Long getFrom();

    String getSubject();

    long getTimestamp();

    boolean getRead();

    Long getCost();

    String getBody();

    boolean isRead();
}
