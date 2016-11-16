package org.devfleet.esi;

import java.util.ArrayList;
import java.util.List;
//TODO
public class Corporation {

    public static class History {

    }

    public static class Member {

    }

    public static class Role {

    }

    private final List<Corporation.History> history = new ArrayList<>();
    private final List<Corporation.Member> members = new ArrayList<>();
    private final List<Corporation.Role> roles = new ArrayList<>();

    private final Long id;

    private String portrait64;
    private String portrait128;
    private String portrait256;

    public Corporation(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public List<History> getHistory() {
        return history;
    }

    public List<Member> getMembers() {
        return members;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public Corporation add(final Corporation.History h) {
        this.history.add(h);
        return this;
    }

    public Corporation add(final Corporation.Member h) {
        this.members.add(h);
        return this;
    }

    public Corporation add(final Corporation.Role h) {
        this.roles.add(h);
        return this;
    }

    public String getPortrait64() {
        return portrait64;
    }

    public void setPortrait64(String portrait64) {
        this.portrait64 = portrait64;
    }

    public String getPortrait128() {
        return portrait128;
    }

    public void setPortrait128(String portrait128) {
        this.portrait128 = portrait128;
    }

    public String getPortrait256() {
        return portrait256;
    }

    public void setPortrait256(String portrait256) {
        this.portrait256 = portrait256;
    }

}
