package org.devfleet.esi.api;

public class KillMailKey {

    private final int id;
    private String hash;

    private KillMailKey(int id) {
        this.id = id;
    }

    public static KillMailKey killMailKey(int id) {
        return new KillMailKey(id);
    }

    public int getId() {
        return id;
    }

    public String getHash() {
        return hash;
    }
}
