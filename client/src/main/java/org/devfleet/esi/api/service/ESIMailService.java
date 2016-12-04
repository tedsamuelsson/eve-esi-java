package org.devfleet.esi.api.service;

import org.devfleet.esi.api.CharacterKey;
import org.devfleet.esi.api.Mail;
import org.devfleet.esi.impl.MailboxData;
import org.devfleet.esi.impl.MailData;

import java.util.List;

public interface ESIMailService {
    List<Mail> getMails(CharacterKey key, Long afterMailID, String... labels);

    List<MailboxData> getMailboxes(CharacterKey key);

    MailData getMailContent(CharacterKey key, Long mailID);
}
