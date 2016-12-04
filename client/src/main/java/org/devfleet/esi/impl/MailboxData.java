package org.devfleet.esi.impl;

import org.devfleet.esi.api.Mailbox;
import org.devfleet.esi.api.MailboxKey;

public class MailboxData implements Mailbox {

    private MailboxKey key = null;
    private Long labelId;
    private String label;

    private Integer color;
    private Integer unread;

    public MailboxData(MailboxKey key) {
        this.key = key;
    }

    @Override
    public MailboxKey getKey() {
        return key;
    }

    @Override
    public Long getLabelId() {
        return labelId;
    }

    public void setLabelId(Long labelId) {
        this.labelId = labelId;
    }

    @Override
    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public Integer getColor() {
        return color;
    }

    public void setColor(Integer color) {
        this.color = color;
    }

    @Override
    public Integer getUnread() {
        return unread;
    }

    public void setUnread(Integer unread) {
        this.unread = unread;
    }
}
