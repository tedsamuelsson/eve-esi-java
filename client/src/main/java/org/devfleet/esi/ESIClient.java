package org.devfleet.esi;

import org.apache.oltu.oauth2.common.token.BasicOAuthToken;
import org.devfleet.esi.api.service.ESIServiceFactory;
import org.devfleet.esi.client.auth.OAuth;
import org.devfleet.esi.impl.service.ESIServiceFactoryImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.devfleet.esi.client.ApiClient;

public class ESIClient {

    private static final Logger LOG = LoggerFactory.getLogger(ESIClient.class);

    private final ApiClient apiClient;
    private final ESIServiceFactory service;

    public ESIClient(final String clientID,
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
        this.service = new ESIServiceFactoryImpl(apiClient);
    }

    public final ESIServiceFactory getService() {
        return this.service;
    }

    public void setAccessToken(final String token) {
        this.apiClient.setAccessToken(token);
    }

    public void onAccessTokenChanged(final String accessToken, final String refreshToken) {

    }

}
