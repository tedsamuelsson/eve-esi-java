package org.devfleet.esi.impl.service;

import org.devfleet.esi.api.*;
import org.devfleet.esi.api.service.ESIKillMailService;
import org.devfleet.esi.impl.ESITransformer;
import org.devfleet.esi.impl.KillMailData;
import org.devfleet.esi.model.GetCharactersCharacterIdKillmailsRecent200Ok;
import org.devfleet.esi.model.GetKillmailsKillmailIdKillmailHashOk;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ESIKillMailServiceImpl implements ESIKillMailService {
    private static Logger LOG = LoggerFactory.getLogger(ESIKillMailServiceImpl.class);

    private final KillmailsApi api;
    private final String datasource;

    public ESIKillMailServiceImpl(String datasource, KillmailsApi api) {
        this.datasource = datasource;
        this.api = api;
    }

    @Override
    public List<KillMail> getKillMails(CharacterKey key, Integer maxCount, Long maxKillID, boolean withContent) {
        try {
            final List<KillMail> kills = new ArrayList<>();
            for (GetCharactersCharacterIdKillmailsRecent200Ok m:
                    api
                            .getCharactersCharacterIdKillmailsRecent(key.getId(), maxCount, maxKillID.intValue(), datasource)
                            .execute()
                            .body()) {
                KillMail km = ESITransformer.transform(m);
                if (withContent) {
                    km = getKillMail(km.getKey());
                }
                kills.add(km);
            }
            return kills;
        }
        catch (IOException e) {
            LOG.error(e.getLocalizedMessage(), e);
            return Collections.emptyList();
        }
    }

    @Override
    public KillMail getKillMail(KillMailKey key) {
        try {
            GetKillmailsKillmailIdKillmailHashOk ok =
                    api
                            .getKillmailsKillmailIdKillmailHash(key.getId(), key.getHash(), datasource)
                            .execute()
                            .body();

            //TODO - fill mail
            return new KillMailData(key);
        }
        catch (IOException e) {
            LOG.error(e.getLocalizedMessage(), e);
            return null;
        }
    }

}
