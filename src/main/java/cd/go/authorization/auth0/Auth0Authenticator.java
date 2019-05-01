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

package cd.go.authorization.auth0;

import cd.go.authorization.auth0.models.AuthConfig;
import cd.go.authorization.auth0.models.LoggedInUserInfo;
import cd.go.authorization.auth0.models.TokenInfo;
import com.auth0.client.auth.AuthAPI;
import com.auth0.json.auth.UserInfo;
import com.auth0.net.Request;

import java.io.IOException;

import static cd.go.authorization.auth0.Auth0Plugin.LOG;
import static java.text.MessageFormat.format;

public class Auth0Authenticator {
    private final Auth0ClientBuilder auth0ClientBuilder;

    public Auth0Authenticator() {
        this(new Auth0ClientBuilder());
    }

    Auth0Authenticator(Auth0ClientBuilder auth0ClientBuilder) {
        this.auth0ClientBuilder = auth0ClientBuilder;
    }

    public LoggedInUserInfo authenticate(TokenInfo tokenInfo, AuthConfig authConfig) throws IOException {
        AuthAPI authAPI = auth0ClientBuilder.authAPI(authConfig.getAuth0Configuration());
        Request<UserInfo> request = authAPI.userInfo(tokenInfo.accessToken());

        UserInfo userInfo = request.execute();

        final LoggedInUserInfo loggedInUserInfo = new LoggedInUserInfo(userInfo.getValues().get("name").toString(), userInfo.getValues().get("email").toString());

        if (authConfig.isUserAllowed(loggedInUserInfo.getUser())) {
            LOG.info(format("[Authenticate] User `{0}` authenticated successfully.", loggedInUserInfo.getUser().username()));
            return loggedInUserInfo;
        }

        return null;
    }

}
