package org.devfleet.esi.impl;

import org.apache.commons.lang3.ArrayUtils;
import org.devfleet.esi.KillMail;
import org.devfleet.esi.Mail;
import org.devfleet.esi.Mailbox;
import org.devfleet.esi.api.LiveApi;
import org.devfleet.esi.model.GetCharactersCharacterIdKillmailsRecent200Ok;
import org.devfleet.esi.model.GetCharactersCharacterIdMail200Ok;
import org.devfleet.esi.model.GetCharactersCharacterIdMailLabelsOkLabels;
import org.devfleet.esi.model.GetKillmailsKillmailIdKillmailHashOk;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class MailAPIImpl {

    private static Logger LOG = LoggerFactory.getLogger(MailAPIImpl.class);

    private final LiveApi liveApi;
    private final String datasource;

    public MailAPIImpl(String datasource, LiveApi liveApi) {

        this.liveApi = liveApi;
        this.datasource = datasource;
    }

    public boolean deleteMail(Long charID, Long mailID) {
        try {
            return this.liveApi
                .deleteCharactersCharacterIdMailMailId(
                    charID.intValue(),
                    mailID.intValue(),
                    this.datasource)
                .execute()
                .isSuccessful();
        }
        catch (IOException e) {
            LOG.error(e.getLocalizedMessage(), e);
            return false;
        }
    }

    public List<Mail> getMails(Long charID, Long afterMailID, String... labels) {
        try {
            org.devfleet.esi.client.CollectionFormats.CSVParams params = null;
            if (ArrayUtils.isNotEmpty(labels)) {
                params = new org.devfleet.esi.client.CollectionFormats.CSVParams();
                params.setParams(Arrays.asList(labels));
            }
            final List<Mail> mails = new ArrayList<>();
            for (GetCharactersCharacterIdMail200Ok object:
                this.liveApi.getCharactersCharacterIdMail(
                    charID.intValue(),
                    params,
                    afterMailID.intValue(),
                    this.datasource)
                    .execute().body()) {
                mails.add(EsiTransformer.transform(object));
            }
            return mails;
        }
        catch (IOException e) {
            LOG.error(e.getLocalizedMessage(), e);
            return Collections.emptyList();
        }
    }

    public List<Mailbox> getMailboxes(Long charID) {
        try {
            final List<Mailbox> mailboxes = new ArrayList<>();
            /*for (GetCharactersCharacterIdMailLists200OkObject object:
                    this.liveApi
                    .getCharactersCharacterIdMailLists(charID.intValue(), this.datasource)
                    .execute()
                    .body()) {
                mailboxes.add(EsiTransformer.transform(object));*/
                for (GetCharactersCharacterIdMailLabelsOkLabels object:
                        liveApi.getCharactersCharacterIdMailLabels(charID.intValue(), this.datasource)
                        .execute()
                        .body()
                        .getLabels()) {
                mailboxes.add(EsiTransformer.transform(object));
            }
            return mailboxes;
        }
        catch (IOException e) {
            LOG.error(e.getLocalizedMessage(), e);
            return Collections.emptyList();
        }
    }

    public Mail getMailContent(Long charID, Long mailID) {
        try {
            return EsiTransformer.transform(
                    liveApi.getCharactersCharacterIdMailMailId(
                    charID.intValue(),
                    mailID.intValue(),
                    this.datasource)
                    .execute()
                    .body(), mailID);
        }
        catch (IOException e) {
            LOG.error(e.getLocalizedMessage(), e);
            return null;
        }
    }

    public Integer postMail(Long charID, Mail mail) {
        try {
            return liveApi.postCharactersCharacterIdMail(
                    charID.intValue(),
                    EsiTransformer.transform(mail),
                    this.datasource)
                    .execute()
                    .body();
        }
        catch (IOException e) {
            LOG.error(e.getLocalizedMessage(), e);
            return null;
        }
    }

    public boolean updateMail(Long charID, Mail mail) {
        try {
            return liveApi.putCharactersCharacterIdMailMailId(
                    charID.intValue(),
                    mail.getId().intValue(),
                    EsiTransformer.transform2(mail),
                    this.datasource)
                    .execute()
                    .isSuccessful();
        }
        catch (IOException e) {
            LOG.error(e.getLocalizedMessage(), e);
            return false;
        }
    }

    public boolean createMailbox(Long charID, Mailbox mailbox) {
        //TODO
        return false;
    }

    public boolean updateMailbox(Long charID, Mailbox mailbox) {
        //TODO
        return false;
    }

    public List<KillMail> getKillMails(Long charID, Integer maxCount, Long maxKillID, boolean withContent) {
        try {
            final List<KillMail> kills = new ArrayList<>();
            for (GetCharactersCharacterIdKillmailsRecent200Ok m:
                    liveApi.getCharactersCharacterIdKillmailsRecent(charID.intValue(), maxCount, maxKillID.intValue(), this.datasource)
                    .execute()
                    .body()) {
                KillMail km = EsiTransformer.transform(m);
                if (withContent) {
                    km = getKillMail(km);
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

    public KillMail getKillMail(KillMail killMail) {
        try {
            GetKillmailsKillmailIdKillmailHashOk ok =
                liveApi.getKillmailsKillmailIdKillmailHash(killMail.getId().intValue(), killMail.getHash(), this.datasource)
                    .execute()
                    .body();

            //TODO - fill mail
            return killMail;
        }
        catch (IOException e) {
            LOG.error(e.getLocalizedMessage(), e);
            return null;
        }
    }
}
