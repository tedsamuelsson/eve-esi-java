package org.devfleet.esi.impl.service;

import org.devfleet.esi.api.CorporationApi;
import org.devfleet.esi.api.CorporationKey;
import org.devfleet.esi.api.CorporationMember;
import org.devfleet.esi.api.service.ESICorporationService;
import org.devfleet.esi.impl.CorporationData;
import org.devfleet.esi.impl.ESITransformer;
import org.devfleet.esi.model.GetCorporationsCorporationIdAlliancehistory200Ok;
import org.devfleet.esi.model.GetCorporationsCorporationIdIconsOk;
import org.devfleet.esi.model.GetCorporationsCorporationIdMembers200Ok;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.Collections.emptyList;

class ESICorporationServiceImpl implements ESICorporationService {

    private static Logger LOG = LoggerFactory.getLogger(ESICorporationServiceImpl.class);

    private final CorporationApi api;

    private final String datasource;

    public ESICorporationServiceImpl(
            String datasource,
            CorporationApi api) {
        this.api = api;
        this.datasource = datasource;
    }

    @Override
    public Optional<CorporationData> tryFetchCorporation(CorporationKey key) {
        try {
            CorporationData corporation =
                    ESITransformer.transform(
                        key,
                        api
                                .getCorporationsCorporationId(key.getId(), datasource)
                                .execute()
                                .body());
            addPortraits(corporation);
            addHistory(corporation);
            return Optional.of(corporation);
        }
        catch (IOException e) {
            LOG.debug(e.getLocalizedMessage(), e);
            LOG.warn(e.getLocalizedMessage());
            return Optional.empty();
        }
    }

    @Override
    public List<CorporationMember> getMembers(CorporationKey key) {
        try {
            List<GetCorporationsCorporationIdMembers200Ok> members =
                api
                        .getCorporationsCorporationIdMembers(key.getId(), datasource)
                        .execute()
                        .body();
            final List<CorporationMember> r = new ArrayList<>(members.size());
            for (GetCorporationsCorporationIdMembers200Ok m: members) {
                r.add(ESITransformer.transform(m));
            }
            return r;
        }
        catch (IOException e) {
            LOG.debug(e.getLocalizedMessage(), e);
            LOG.warn(e.getLocalizedMessage());
            return emptyList();
        }
    }

    private void addPortraits(CorporationData to) {
        try {
            GetCorporationsCorporationIdIconsOk icons =
                    api
                            .getCorporationsCorporationIdIcons(to.getKey().getId(), datasource)
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

    private void addHistory(final CorporationData to) {
        try {
            List<GetCorporationsCorporationIdAlliancehistory200Ok> history =
                    api
                            .getCorporationsCorporationIdAlliancehistory(to.getKey().getId(), datasource)
                            .execute()
                            .body();
            for (GetCorporationsCorporationIdAlliancehistory200Ok h: history) {
                to.add(ESITransformer.transform(h));
            }
        }
        catch (IOException e) {
            LOG.debug(e.getLocalizedMessage(), e);
            LOG.warn(e.getLocalizedMessage());
        }
    }
}
