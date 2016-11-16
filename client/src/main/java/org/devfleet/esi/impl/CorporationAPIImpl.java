package org.devfleet.esi.impl;

import org.devfleet.esi.Corporation;
import org.devfleet.esi.api.LiveApi;
import org.devfleet.esi.model.GetCorporationsCorporationIdAlliancehistory200Ok;
import org.devfleet.esi.model.GetCorporationsCorporationIdIconsOk;
import org.devfleet.esi.model.GetCorporationsCorporationIdMembers200Ok;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class CorporationAPIImpl {

    private static Logger LOG = LoggerFactory.getLogger(CorporationAPIImpl.class);

    private final LiveApi liveApi;

    private final String datasource;

    public CorporationAPIImpl(
            String datasource,
            LiveApi liveApi) {
        this.liveApi = liveApi;
        this.datasource = datasource;
    }

    public Corporation getCorporation(Long corpID) {
        try {
            Corporation corporation =
                    EsiTransformer.transform(
                        corpID,
                        this.liveApi
                        .getCorporationsCorporationId(corpID.intValue(), this.datasource)
                        .execute()
                        .body());
            addPortraits(corporation);
            addHistory(corporation);
            return corporation;
        }
        catch (IOException e) {
            LOG.debug(e.getLocalizedMessage(), e);
            LOG.warn(e.getLocalizedMessage());
            return null;
        }
    }

    public List<Corporation.Member> getMembers(Long corpID) {
        try {
            List<GetCorporationsCorporationIdMembers200Ok> members =
                this.liveApi.getCorporationsCorporationIdMembers(corpID.intValue(), this.datasource)
                    .execute()
                    .body();
            final List<Corporation.Member> r = new ArrayList<>(members.size());
            for (GetCorporationsCorporationIdMembers200Ok m: members) {
                r.add(EsiTransformer.transform(m));
            }
            return r;
        }
        catch (IOException e) {
            LOG.debug(e.getLocalizedMessage(), e);
            LOG.warn(e.getLocalizedMessage());
            return Collections.emptyList();
        }
    }

    private void addPortraits(final Corporation to) {
        try {
            GetCorporationsCorporationIdIconsOk icons =
                    this.liveApi
                            .getCorporationsCorporationIdIcons(to.getId().intValue(), this.datasource)
                            .execute()
                            .body();
            to.setPortrait64(icons.getPx64x64());
            to.setPortrait128(icons.getPx128x128());
            to.setPortrait256(icons.getPx256x256());
        }
        catch (IOException e) {
            LOG.debug(e.getLocalizedMessage(), e);
            LOG.warn(e.getLocalizedMessage());
        }
    }

    private void addHistory(final Corporation to) {
        try {
            List<GetCorporationsCorporationIdAlliancehistory200Ok> history =
                    this.liveApi
                            .getCorporationsCorporationIdAlliancehistory(to.getId().intValue(), this.datasource)
                            .execute()
                            .body();
            for (GetCorporationsCorporationIdAlliancehistory200Ok h: history) {
                to.add(EsiTransformer.transform(h));
            }
        }
        catch (IOException e) {
            LOG.debug(e.getLocalizedMessage(), e);
            LOG.warn(e.getLocalizedMessage());
        }
    }
}
