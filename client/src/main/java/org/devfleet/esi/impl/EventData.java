package org.devfleet.esi.impl;

import org.devfleet.esi.api.Event;
import org.devfleet.esi.api.EventKey;

public class EventData implements Event {

    private final EventKey key;

    public EventData(EventKey key) {
        this.key = key;
    }

    @Override
    public EventKey getKey() {
        return key;
    }
}
