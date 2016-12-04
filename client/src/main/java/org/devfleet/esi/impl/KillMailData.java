package org.devfleet.esi.impl;

import org.devfleet.esi.api.KillMail;
import org.devfleet.esi.api.KillMailKey;

public class KillMailData implements KillMail {

    private KillMailKey key;
    private Long time;

    private String hash;

    public KillMailData(KillMailKey key) {
        this.key = key;
    }

    @Override
    public KillMailKey getKey() {
        return key;
    }

    @Override
    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }
}
