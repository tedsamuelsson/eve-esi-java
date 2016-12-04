package org.devfleet.esi.builder;

import org.devfleet.esi.api.Label;
import org.devfleet.esi.api.LabelKey;
import org.devfleet.esi.impl.MailData;
import org.devfleet.esi.api.MailKey;
import org.devfleet.esi.model.CharacterscharacterIdmailRecipients;
import org.devfleet.esi.model.GetCharactersCharacterIdMailMailIdOkRecipients;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MailBuilder {

    private MailBuilder() {
    }

    public static MailBuilder mailBuilder() {
        return new MailBuilder();
    }

    private MailKey key;
    private Long from;
    private Long cost;
    private String body;
    private String subject;
    private long timestamp;
    private boolean read;
    private Map<LabelKey, Label> labels = new HashMap<>();
    private List<MailData.Recipient> recipients = new ArrayList<>();

    public MailData build(){
        return new MailData(key, from, cost, body, subject, timestamp, read, labels, recipients);
    }

    public MailBuilder key(MailKey key) {
        this.key = key;
        return this;
    }

    public MailBuilder from(Long from) {
        this.from = from;
        return this;
    }

    public MailBuilder subject(String subject) {
        this.subject = subject;
        return this;
    }

    public MailBuilder timestamp(long timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public MailBuilder read(boolean read) {
        this.read = read;
        return this;
    }

    public MailBuilder cost(Long cost) {
        this.cost = cost;
        return this;
    }

    public MailBuilder body(String body) {
        this.body = body;
        return this;
    }

    public MailBuilder recipient(final Long id, final String type) {
        this.recipients.add(new MailData.Recipient().setId(id).setType(type));
        return this;
    }

    public MailBuilder recipient(final MailData.Recipient recipient) {
        this.recipients.add(recipient);
        return this;
    }

    public MailBuilder withLabel(final LabelKey key, Label label) {
        this.labels.put(key, label);
        return this;
    }

    public MailBuilder recipients(List<GetCharactersCharacterIdMailMailIdOkRecipients> recipients) {
        for (GetCharactersCharacterIdMailMailIdOkRecipients r: recipients) {
            recipient(r.getRecipientId().longValue(), r.getRecipientType().toString());
        }
        return this;
    }

    public MailBuilder recipients2(List<CharacterscharacterIdmailRecipients> recipients) {
        for (CharacterscharacterIdmailRecipients r: recipients) {
            recipient(r.getRecipientId().longValue(), r.getRecipientType().toString());
        }

        return this;
    }
}
