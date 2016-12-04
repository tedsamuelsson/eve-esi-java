package org.devfleet.esi.impl;

import org.devfleet.esi.api.Label;
import org.devfleet.esi.api.LabelKey;
import org.devfleet.esi.api.MailKey;
import org.devfleet.esi.api.Mail;

import java.util.List;
import java.util.Map;

public class MailData implements Mail {

    private final MailKey key;
    private final Long from;
    private final Long cost;
    private final String body;
    private final String subject;
    private final long timestamp;
    private final boolean read;
    private final Map<LabelKey, Label> labels;
    private final List<MailData.Recipient> recipients;

    public MailData(MailKey key, Long from, Long cost, String body, String subject, long timestamp, boolean read, Map<LabelKey, Label> labels, List<MailData.Recipient> recipients) {
        this.key = key;
        this.from = from;
        this.cost = cost;
        this.body = body;
        this.subject = subject;
        this.timestamp = timestamp;
        this.read = read;
        this.labels = labels;
        this.recipients = recipients;
    }

    @Override
    public Map<LabelKey, Label> getLabels() {
        return labels;
    }

    @Override
    public List<Recipient> getRecipients() {
        return recipients;
    }

    @Override
    public MailKey getKey() {
        return key;
    }

    @Override
    public Long getFrom() {
        return from;
    }

    @Override
    public String getSubject() {
        return subject;
    }

    @Override
    public long getTimestamp() {
        return timestamp;
    }

    @Override
    public boolean getRead() {
        return read;
    }

    @Override
    public Long getCost() {
        return cost;
    }

    @Override
    public String getBody() {
        return body;
    }

    @Override
    public boolean isRead() {
        return read;
    }
}
