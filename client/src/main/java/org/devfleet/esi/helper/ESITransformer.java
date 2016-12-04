package org.devfleet.esi.helper;

import org.devfleet.esi.api.*;
import org.devfleet.esi.impl.*;
import org.devfleet.esi.model.CharacterscharacterIdmailRecipients1;
import org.devfleet.esi.model.GetCharactersCharacterIdCalendar200Ok;
import org.devfleet.esi.model.GetCharactersCharacterIdCalendarEventIdOk;
import org.devfleet.esi.model.GetCharactersCharacterIdCorporationhistory200Ok;
import org.devfleet.esi.model.GetCharactersCharacterIdKillmailsRecent200Ok;
import org.devfleet.esi.model.GetCharactersCharacterIdMail200Ok;
import org.devfleet.esi.model.GetCharactersCharacterIdMailLabelsOkLabels;
import org.devfleet.esi.model.GetCharactersCharacterIdMailMailIdOk;
import org.devfleet.esi.model.GetCharactersCharacterIdOk;
import org.devfleet.esi.model.GetCorporationsCorporationIdAlliancehistory200Ok;
import org.devfleet.esi.model.GetCorporationsCorporationIdMembers200Ok;
import org.devfleet.esi.model.GetCorporationsCorporationIdOk;
import org.devfleet.esi.model.PostCharactersCharacterIdMailMail;
import org.devfleet.esi.model.PutCharactersCharacterIdMailMailIdContents;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

import static org.devfleet.esi.api.MailKey.mailKey;
import static org.devfleet.esi.api.KillMailKey.killMailKey;
import static org.devfleet.esi.api.MailboxKey.mailboxKey;
import static org.devfleet.esi.builder.MailBuilder.mailBuilder;

public class ESITransformer {

    private ESITransformer() {}

    public static CharacterData transform(CharacterKey key, GetCharactersCharacterIdOk c) {
        return new CharacterData(key);
    }

    public static CorporationData transform(CorporationKey key, GetCorporationsCorporationIdOk c) {
        return new CorporationData(key);
    }

    public static CharacterHistory transform(GetCharactersCharacterIdCorporationhistory200Ok h) {
        return new CharacterHistoryData();
    }

    public static CorporationHistory transform(GetCorporationsCorporationIdAlliancehistory200Ok h) {
        return new CorporationHistoryData();
    }

    public static CorporationMember transform(GetCorporationsCorporationIdMembers200Ok m) {
        return new CorporationMemberData();
    }

    public static Event transform(GetCharactersCharacterIdCalendar200Ok meta,
            GetCharactersCharacterIdCalendarEventIdOk details) {
        return new EventData(EventKey.eventKey(Integer.valueOf(meta.getEventId())));
    }

    public static MailData transform(GetCharactersCharacterIdMailMailIdOk object, final Long mailId) {
        return mailBuilder()
                .key(mailKey(mailId))
                .from(object.getFrom().longValue())
                .read(object.getRead())
                .subject(object.getSubject())
                .body(object.getBody())
                .timestamp(object.getTimestamp().getMillis())
                .recipients(object.getRecipients())
                .build();
    }

    public static Mail transform(GetCharactersCharacterIdMail200Ok object) {
        return mailBuilder()
                .key(mailKey(object.getMailId()))
                .from(object.getFrom().longValue())
                .read(object.getIsRead())
                .subject(object.getSubject())
                .timestamp(object.getTimestamp().getMillis())
                .recipients2(object.getRecipients())
                .build();
    }

    public static PostCharactersCharacterIdMailMail transform(final MailData mail) {
        PostCharactersCharacterIdMailMail object = new PostCharactersCharacterIdMailMail();
        object.setSubject(mail.getSubject());
        object.setApprovedCost(mail.getCost());
        object.setBody(mail.getBody());

        List<CharacterscharacterIdmailRecipients1> recipients = new ArrayList<>();
        for (MailData.Recipient r: mail.getRecipients()) {
            final CharacterscharacterIdmailRecipients1 r1 = new CharacterscharacterIdmailRecipients1();
            r1.setRecipientId(r.getId().intValue());
            r1.setRecipientType(recipientType(r.getType()));
            recipients.add(r1);
        }
        object.setRecipients(recipients);
        return object;
    }

    public static PutCharactersCharacterIdMailMailIdContents transform2(final MailData mail) {
        PutCharactersCharacterIdMailMailIdContents contents = new PutCharactersCharacterIdMailMailIdContents();
//        contents.setLabels(new ArrayList<LabelKey>(mail.getLabels().keySet()));
        contents.setRead(mail.getRead());
        return contents;
    }

    public static KillMail transform(GetCharactersCharacterIdKillmailsRecent200Ok object) {
        KillMailData km = new KillMailData(killMailKey(object.getKillmailId()));
        km.setHash(object.getKillmailHash());
        return km;
    }

    public static MailboxData transform(GetCharactersCharacterIdMailLabelsOkLabels object) {
        MailboxData m = new MailboxData(mailboxKey(object.getLabelId()));
        m.setLabel(object.getName());
        m.setUnread(object.getUnreadCount());
        return m;
    }

    private static CharacterscharacterIdmailRecipients1.RecipientTypeEnum recipientType(final String type) {
        for (CharacterscharacterIdmailRecipients1.RecipientTypeEnum e: EnumSet.allOf(CharacterscharacterIdmailRecipients1.RecipientTypeEnum.class)) {
            if (e.name().equalsIgnoreCase(type)) {
                return e;
            }
        }
        return null;
    }
}
