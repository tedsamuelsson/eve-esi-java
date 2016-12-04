package org.devfleet.esi.impl.service;

import org.devfleet.esi.api.service.*;
import org.devfleet.esi.api.*;
import org.devfleet.esi.api.Character;
import org.devfleet.esi.client.ApiClient;
import org.devfleet.esi.impl.*;

import java.util.List;
import java.util.Optional;

public class ESIServiceFactoryImpl implements ESIServiceFactory {

    private final ESICharacterService characterService;
    private final ESICorporationService corporationService;
    private final ESIMailService mailService;
    private final ESIKillMailService killMailService;

    public ESIServiceFactoryImpl(final ApiClient client) {
        this(client, "tranquility");
    }

    public ESIServiceFactoryImpl(final ApiClient client, final String datasource) {
        this(datasource, client);
    }

    public ESIServiceFactoryImpl(final String datasource, ApiClient client) {
        characterService = new ESICharacterServiceImpl(datasource, client.createService(CharacterApi.class));
        corporationService = new ESICorporationServiceImpl(datasource, client.createService(CorporationApi.class));
        mailService = new ESIMailServiceImpl(datasource, client.createService(MailApi.class));
        killMailService = new ESIKillMailServiceImpl(datasource, client.createService(KillmailsApi.class));
    }

    @Override
    public ESICharacterService getCharacterService() {
        return characterService;
    }

    @Override
    public Optional<Character> tryFetchCharacter(CharacterKey key) {
        return characterService.tryFetchCharacter(key);
    }

    @Override
    public Optional<CorporationData> getCorporation(CorporationKey key) {
        return corporationService.tryFetchCorporation(key);
    }

    @Override
    public List<CorporationMember> getMembers(CorporationKey key) {
        return this.corporationService.getMembers(key);
    }

    @Override
    public List<Mail> getMails(CharacterKey key, Long afterMailID, String... labels) {
        return mailService.getMails(key, afterMailID, labels);
    }

    @Override
    public List<MailboxData> getMailboxes(CharacterKey key) {
        return mailService.getMailboxes(key);
    }

    @Override
    public MailData getMailContent(CharacterKey key, Long mailID) {
        return mailService.getMailContent(key, mailID);
    }

    @Override
    public List<KillMail> getKillMails(CharacterKey key, Integer maxCount, Long maxKillID, boolean withContent) {
        return killMailService.getKillMails(key, maxCount, maxKillID, withContent);
    }

    @Override
    public KillMail getKillMail(KillMailKey key) {
        return killMailService.getKillMail(key);
    }
}
