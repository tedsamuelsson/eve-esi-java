package org.devfleet.esi;

import org.apache.oltu.oauth2.common.token.BasicOAuthToken;
import org.devfleet.esi.client.auth.OAuth;
import org.devfleet.esi.impl.EsiServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.devfleet.esi.client.ApiClient;

public class EsiClient {

    private static final Logger LOG = LoggerFactory.getLogger(EsiClient.class);

    private final ApiClient apiClient;
    private final EsiService service;

    public EsiClient(final String clientID,
                     final String clientSecret,
                     final String redirectUri) {

        this.apiClient = new ApiClient("evesso");
        this.apiClient.createDefaultAdapter();
        this.apiClient.configureAuthorizationFlow(clientID, clientSecret, redirectUri);
        this.apiClient.registerAccessTokenListener(new OAuth.AccessTokenListener() {
            public void notify(BasicOAuthToken token) {
                onAccessTokenChanged(token.getAccessToken(), token.getRefreshToken());
            }
        });
        this.service = new EsiServiceImpl(apiClient);
    }

    public final EsiService getService() {
        return this.service;
    }

    public void setAccessToken(final String token) {
        this.apiClient.setAccessToken(token);
    }

    public void onAccessTokenChanged(final String accessToken, final String refreshToken) {

    }

}
