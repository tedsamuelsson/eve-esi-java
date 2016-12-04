package org.devfleet.esi.helper;

import org.devfleet.esi.client.ApiClient;
import org.devfleet.esi.impl.ESICharacterServiceImplTest;
import org.devfleet.esi.impl.service.ESIServiceFactoryImpl;

public class ESIFactoryHelper {
    public static ESIServiceFactoryImpl getESIServiceFactory(final ESICharacterServiceImplTest esiCharacterServiceImplTest, String clientID, String clientSecret, String redirectUri) {
        ApiClient apiClient;
        apiClient = new ApiClient("evesso");
        apiClient.createDefaultAdapter();
        apiClient.configureAuthorizationFlow(clientID, clientSecret, redirectUri);
        apiClient.registerAccessTokenListener(token -> onAccessTokenChanged(token.getAccessToken(), token.getRefreshToken()));
        return new ESIServiceFactoryImpl(apiClient);
    }

    private static void onAccessTokenChanged(final String accessToken, final String refreshToken) {
    }
}
