package org.devfleet.esi.api.service;

import org.devfleet.esi.api.CorporationKey;
import org.devfleet.esi.api.CorporationMember;
import org.devfleet.esi.impl.CorporationData;

import java.util.List;
import java.util.Optional;

public interface ESICorporationService {
    Optional<CorporationData> tryFetchCorporation(CorporationKey key);

    List<CorporationMember> getMembers(CorporationKey key);
}
