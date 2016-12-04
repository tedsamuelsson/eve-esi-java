package org.devfleet.esi.api;

public class EventKey {

    private final int id;

    private EventKey(int id) {
        this.id = id;
    }

    public static EventKey eventKey(int id) {
        return new EventKey(id);
    }

    public int getId() {
        return id;
    }
}
