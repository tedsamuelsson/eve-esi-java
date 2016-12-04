package org.devfleet.esi.impl.service;

import org.apache.commons.lang3.ArrayUtils;
import org.devfleet.esi.api.CharacterKey;
import org.devfleet.esi.api.service.ESIMailService;
import org.devfleet.esi.api.Mail;
import org.devfleet.esi.api.MailApi;
import org.devfleet.esi.helper.ESITransformer;
import org.devfleet.esi.impl.MailData;
import org.devfleet.esi.impl.MailboxData;
import org.devfleet.esi.model.GetCharactersCharacterIdMail200Ok;
import org.devfleet.esi.model.GetCharactersCharacterIdMailLabelsOkLabels;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class ESIMailServiceImpl implements ESIMailService {

    private static Logger LOG = LoggerFactory.getLogger(ESIMailServiceImpl.class);

    private final MailApi api;
    private final String datasource;

    public ESIMailServiceImpl(String datasource, MailApi api) {

        this.api = api;
        this.datasource = datasource;
    }

    @Override
    public List<Mail> getMails(CharacterKey key, Long afterMailID, String... labels) {
        try {
            org.devfleet.esi.client.CollectionFormats.CSVParams params = null;
            if (ArrayUtils.isNotEmpty(labels)) {
                params = new org.devfleet.esi.client.CollectionFormats.CSVParams();
                params.setParams(Arrays.asList(labels));
            }
            final List<Mail> mails = new ArrayList<>();
            for (GetCharactersCharacterIdMail200Ok object:
                this.api.getCharactersCharacterIdMail(
                    key.getId(),
                    params,
                    afterMailID.intValue(),
                    this.datasource)
                    .execute().body()) {
                mails.add(ESITransformer.transform(object));
            }
            return mails;
        }
        catch (IOException e) {
            LOG.error(e.getLocalizedMessage(), e);
            return Collections.emptyList();
        }
    }

    @Override
    public List<MailboxData> getMailboxes(CharacterKey key) {
        try {
            final List<MailboxData> mailboxes = new ArrayList<>();
            /*for (GetCharactersCharacterIdMailLists200OkObject object:
                    this.api
                    .getCharactersCharacterIdMailLists(charID.intValue(), this.datasource)
                    .execute()
                    .body()) {
                mailboxes.add(ESITransformer.transform(object));*/
                for (GetCharactersCharacterIdMailLabelsOkLabels object:
                        api.getCharactersCharacterIdMailLabels(key.getId(), this.datasource)
                        .execute()
                        .body()
                        .getLabels()) {
                mailboxes.add(ESITransformer.transform(object));
            }
            return mailboxes;
        }
        catch (IOException e) {
            LOG.error(e.getLocalizedMessage(), e);
            return Collections.emptyList();
        }
    }

    @Override
    public MailData getMailContent(CharacterKey key, Long mailID) {
        try {
            return ESITransformer.transform(
                    api.getCharactersCharacterIdMailMailId(
                    key.getId(),
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
}
