package org.devfleet.esi.api.service;

import org.devfleet.esi.api.CharacterKey;
import org.devfleet.esi.api.KillMail;
import org.devfleet.esi.api.KillMailKey;

import java.util.List;

public interface ESIKillMailService {
    List<KillMail> getKillMails(CharacterKey key, Integer maxCount, Long maxKillID, boolean withContent);

    KillMail getKillMail(KillMailKey key);
}
