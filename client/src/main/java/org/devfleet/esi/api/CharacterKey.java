package org.devfleet.esi.api;

public class CharacterKey {

    private int id;

    private CharacterKey(int id) {
        this.id = id;
    }

    public static CharacterKey characterKey(int id) {
        return new CharacterKey(id);
    }

    public int getId() {
        return id;
    }
}
