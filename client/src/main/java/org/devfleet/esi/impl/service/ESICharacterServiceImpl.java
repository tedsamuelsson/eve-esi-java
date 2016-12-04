package org.devfleet.esi.impl.service;

import org.devfleet.esi.api.Character;
import org.devfleet.esi.api.CharacterApi;
import org.devfleet.esi.api.CharacterKey;
import org.devfleet.esi.api.service.ESICharacterService;
import org.devfleet.esi.impl.CharacterData;
import org.devfleet.esi.helper.ESITransformer;
import org.devfleet.esi.model.GetCharactersCharacterIdCorporationhistory200Ok;
import org.devfleet.esi.model.GetCharactersCharacterIdOk;
import org.devfleet.esi.model.GetCharactersCharacterIdPortraitOk;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class ESICharacterServiceImpl implements ESICharacterService {

    private static Logger LOG = LoggerFactory.getLogger(ESICharacterServiceImpl.class);

    private final String datasource;
    private final CharacterApi api;

    public ESICharacterServiceImpl(String datasource, CharacterApi api) {
        this.datasource = datasource;
        this.api = api;
    }

    @Override
    public Optional<Character> tryFetchCharacter(CharacterKey key) {
        try {
            GetCharactersCharacterIdOk body = api
                    .getCharactersCharacterId(key.getId(), datasource)
                    .execute()
                    .body();
            CharacterData character = ESITransformer.transform(
                    key,
                    body);
            addPortraits(character);
            addHistory(character);
            return Optional.of(character);
        }
        catch (IOException e) {
            LOG.debug(e.getLocalizedMessage(), e);
            LOG.warn(e.getLocalizedMessage());
            return Optional.empty();
        }
    }

    private void addPortraits(final CharacterData to) {
        try {
            GetCharactersCharacterIdPortraitOk portraits =
                    api
                            .getCharactersCharacterIdPortrait(to.getKey().getId(), datasource)
                            .execute()
                            .body();
            to.setPortrait64(portraits.getPx64x64());
            to.setPortrait128(portraits.getPx128x128());
            to.setPortrait256(portraits.getPx256x256());
            to.setPortrait512(portraits.getPx512x512());
        }
        catch (IOException e) {
            LOG.debug(e.getLocalizedMessage(), e);
            LOG.warn(e.getLocalizedMessage());
        }
    }

    private void addHistory(final CharacterData to) {
        try {
            List<GetCharactersCharacterIdCorporationhistory200Ok> history =
                    api
                            .getCharactersCharacterIdCorporationhistory(to.getKey().getId(), datasource)
                            .execute()
                            .body();
            for (GetCharactersCharacterIdCorporationhistory200Ok h: history) {
                to.add(ESITransformer.transform(h));
            }
        }
        catch (IOException e) {
            LOG.debug(e.getLocalizedMessage(), e);
            LOG.warn(e.getLocalizedMessage());
        }
    }

}
