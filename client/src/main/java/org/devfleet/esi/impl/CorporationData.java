package org.devfleet.esi.impl;

import org.devfleet.esi.api.*;
import org.devfleet.esi.api.Character;

import java.util.ArrayList;
import java.util.List;
//TODO
public class CorporationData implements Corporation {

    private final List<CorporationHistory> history = new ArrayList<>();
    private final List<CorporationMember> members = new ArrayList<>();
    private final List<CorporationRole> roles = new ArrayList<>();

    private final CorporationKey key;

    private String portrait64;
    private String portrait128;
    private String portrait256;

    public CorporationData(CorporationKey key) {
        this.key = key;
    }

    public CorporationKey getKey() {
        return key;
    }

    @Override
    public List<CorporationHistory> getHistory() {
        return history;
    }

    @Override
    public List<CorporationMember> getMembers() {
        return members;
    }

    @Override
    public List<CorporationRole> getRoles() {
        return roles;
    }

    public void add(CorporationHistory h) {
        history.add(h);
    }

    public void add(CorporationMember h) {
        members.add(h);
    }

    public void add(CorporationRole h) {
        roles.add(h);
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

}
