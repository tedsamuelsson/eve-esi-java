package org.devfleet.esi.api;

import java.util.List;

public interface Character {
    CharacterKey getKey();

    List<CharacterHistory> getHistory();

    String getPortrait64();

    String getPortrait128();

    String getPortrait256();

    String getPortrait512();

}
