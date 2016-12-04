package org.devfleet.esi.impl;

import org.devfleet.esi.api.Character;
import org.devfleet.esi.api.CharacterKey;
import org.devfleet.esi.api.service.ESICharacterService;
import org.devfleet.esi.api.service.ESIServiceFactory;
import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import static org.devfleet.esi.api.CharacterKey.characterKey;
import static org.devfleet.esi.helper.ESIFactoryHelper.getESIServiceFactory;

public class ESICharacterServiceImplTest {

    private static final CharacterKey CCP_FOZZIE = characterKey(92168555);

    private ESICharacterService characterService;

    @Before
    public void before() {
        String clientID = "";
        String clientSecret = "";
        String redirectUri = "";

        ESIServiceFactory factory = getESIServiceFactory(this, clientID, clientSecret, redirectUri);
        characterService = factory.getCharacterService();
    }


    @Test
    public void characterServiceSmokeTest() {
        Optional<Character> character = characterService.tryFetchCharacter(CCP_FOZZIE);
        Assert.assertThat(character.isPresent(), Is.is(true));
    }
}