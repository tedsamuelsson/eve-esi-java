package org.devfleet.esi.api.service;

import org.devfleet.esi.api.Character;
import org.devfleet.esi.api.CharacterKey;

import java.util.Optional;

public interface ESICharacterService {
    Optional<Character> tryFetchCharacter(CharacterKey key);
}
