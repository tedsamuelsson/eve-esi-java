package org.devfleet.esi.api;

public class CorporationKey {

    private final int id;

    private CorporationKey(int id) {
        this.id = id;
    }

    public static CorporationKey corporationKey(int id) {
        return new CorporationKey(id);
    }

    public int getId() {
        return id;
    }
}
