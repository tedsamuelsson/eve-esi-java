package org.devfleet.esi.api;

import java.util.List;

public interface Corporation {
    abstract List<CorporationHistory> getHistory();

    abstract List<CorporationMember> getMembers();

    abstract List<CorporationRole> getRoles();

    String getPortrait64();

    String getPortrait128();

    String getPortrait256();
}
