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
import cd.go.authorization.auth0.requests.GetAuthorizationServerUrlRequest;
import com.thoughtworks.go.plugin.api.response.DefaultGoPluginApiResponse;
import com.thoughtworks.go.plugin.api.response.GoPluginApiResponse;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.Collections;

import static cd.go.authorization.auth0.Auth0Plugin.LOG;
import static cd.go.authorization.auth0.utils.Util.GSON;

public class GetAuthorizationServerUrlRequestExecutor implements RequestExecutor {
    private final GetAuthorizationServerUrlRequest request;

    public GetAuthorizationServerUrlRequestExecutor(GetAuthorizationServerUrlRequest request) {
        this.request = request;
    }

    public GoPluginApiResponse execute() throws Exception {
        if (request.authConfigs() == null || request.authConfigs().isEmpty()) {
            throw new NoAuthorizationConfigurationException("[Authorization Server Url] No authorization configuration found.");
        }

        LOG.debug("[Get Authorization Server URL] Getting authorization server url authAPI auth config.");

        final AuthConfig authConfig = request.authConfigs().get(0);
        final Auth0Configuration auth0Configuration = authConfig.getAuth0Configuration();

        String authorizationServerUrl = new Auth0ClientBuilder().authAPI(auth0Configuration)
                .authorizeUrl(auth0Configuration.callbackURL())
                .withScope(auth0Configuration.scope())
                .withState(state())
                .build();

        return DefaultGoPluginApiResponse.success(GSON.toJson(Collections.singletonMap("authorization_server_url", authorizationServerUrl)));
    }

    private String state() {
        final SecureRandom sr = new SecureRandom();
        final byte[] randomBytes = new byte[32];
        sr.nextBytes(randomBytes);
        return Base64.getEncoder().encodeToString(randomBytes);
    }

}
