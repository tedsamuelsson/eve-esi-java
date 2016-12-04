package org.devfleet.esi.impl;

import org.devfleet.esi.api.Calendar;
import org.devfleet.esi.api.Event;

import java.util.ArrayList;
import java.util.List;

public class CalendarData implements Calendar {
    private final List<Event> events = new ArrayList<>();

    @Override
    public List<Event> getEvents() {
        return events;
    }

    public void addEvent(Event event) {
        events.add(event);
    }
}
