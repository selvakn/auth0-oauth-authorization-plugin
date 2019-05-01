/*
 * Copyright 2017 ThoughtWorks, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cd.go.authorization.auth0.executors;

import cd.go.authorization.auth0.Auth0ClientBuilder;
import cd.go.authorization.auth0.exceptions.NoAuthorizationConfigurationException;
import cd.go.authorization.auth0.models.Auth0Configuration;
import cd.go.authorization.auth0.models.AuthConfig;
import cd.go.authorization.auth0.models.TokenInfo;
import cd.go.authorization.auth0.requests.FetchAccessTokenRequest;
import com.auth0.client.auth.AuthAPI;
import com.auth0.json.auth.TokenHolder;
import com.auth0.net.AuthRequest;
import com.thoughtworks.go.plugin.api.response.DefaultGoPluginApiResponse;
import com.thoughtworks.go.plugin.api.response.GoPluginApiResponse;
import okhttp3.OkHttpClient;

import static cd.go.authorization.auth0.Auth0Plugin.LOG;

public class FetchAccessTokenRequestExecutor implements RequestExecutor {
    private final FetchAccessTokenRequest request;

    public FetchAccessTokenRequestExecutor(FetchAccessTokenRequest request) {
        this(request, new OkHttpClient());
    }

    FetchAccessTokenRequestExecutor(FetchAccessTokenRequest request, OkHttpClient httpClient) {
        this.request = request;
    }

    public GoPluginApiResponse execute() throws Exception {
        if (request.authConfigs() == null || request.authConfigs().isEmpty()) {
            throw new NoAuthorizationConfigurationException("[Get Access Token] No authorization configuration found.");
        }

        if (!request.requestParameters().containsKey("code")) {
            throw new IllegalArgumentException("Get Access Token] Expecting `code` in request params, but not received.");
        }

        final AuthConfig authConfig = request.authConfigs().get(0);
        final Auth0Configuration config = authConfig.getAuth0Configuration();

        AuthAPI authAPI = new Auth0ClientBuilder().authAPI(config);
        AuthRequest authRequest = authAPI
                .exchangeCode(request.requestParameters().get("code"), config.callbackURL())
                .setScope("openid contacts");
        TokenHolder tokenHolder = authRequest.execute();

        LOG.info("[Get Access Token] Access token fetched successfully.");
        final TokenInfo tokenInfo = new TokenInfo(tokenHolder.getAccessToken(), tokenHolder.getTokenType());
        return DefaultGoPluginApiResponse.success(tokenInfo.toJSON());
    }

}
