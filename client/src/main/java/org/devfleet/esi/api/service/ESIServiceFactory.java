package org.devfleet.esi.api.service;

import org.devfleet.esi.api.*;
import org.devfleet.esi.api.Character;
import org.devfleet.esi.api.service.ESICharacterService;
import org.devfleet.esi.impl.CorporationData;
import org.devfleet.esi.impl.MailboxData;

import java.util.List;
import java.util.Optional;

public interface ESIServiceFactory {

    Optional<Character> tryFetchCharacter(CharacterKey key);

    Optional<CorporationData> getCorporation(CorporationKey key);

    List<CorporationMember> getMembers(CorporationKey key);

    List<Mail> getMails(CharacterKey key, final Long afterMailID, final String... labels);

    List<MailboxData> getMailboxes(CharacterKey key);

    Mail getMailContent(CharacterKey key, final Long mailID);

    List<KillMail> getKillMails(CharacterKey key, Integer maxCount, Long maxKillID, boolean withContent);

    KillMail getKillMail(KillMailKey key);

    ESICharacterService getCharacterService();
}
