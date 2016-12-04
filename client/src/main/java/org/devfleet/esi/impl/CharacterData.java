package org.devfleet.esi.impl;

import org.devfleet.esi.api.CharacterHistory;
import org.devfleet.esi.api.CharacterKey;
import org.devfleet.esi.api.Character;

import java.util.ArrayList;
import java.util.List;

public class CharacterData implements Character {

    private final List<CharacterHistory> history = new ArrayList<>();
    private final CharacterKey key;

    private String portrait64;
    private String portrait128;
    private String portrait256;
    private String portrait512;

    CharacterData(CharacterKey key) {
        this.key = key;
    }

    @Override
    public CharacterKey getKey() {
        return key;
    }

    @Override
    public List<CharacterHistory> getHistory() {
        return history;
    }

    public void add(CharacterHistory h) {
        this.history.add(h);
    }

    @Override
    public String getPortrait64() {
        return portrait64;
    }

    public void setPortrait64(String portrait64) {
        this.portrait64 = portrait64;
    }

    @Override
    public String getPortrait128() {
        return portrait128;
    }

    public void setPortrait128(String portrait128) {
        this.portrait128 = portrait128;
    }

    @Override
    public String getPortrait256() {
        return portrait256;
    }

    public void setPortrait256(String portrait256) {
        this.portrait256 = portrait256;
    }

    @Override
    public String getPortrait512() {
        return portrait512;
    }

    public void setPortrait512(String portrait512) {
        this.portrait512 = portrait512;
    }

}
