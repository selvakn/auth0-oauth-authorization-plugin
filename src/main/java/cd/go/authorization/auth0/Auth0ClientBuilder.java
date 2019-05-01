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

import cd.go.authorization.auth0.models.Auth0Configuration;
import com.auth0.client.auth.AuthAPI;
import com.auth0.client.mgmt.ManagementAPI;

public class Auth0ClientBuilder {

    public AuthAPI authAPI(Auth0Configuration auth0Configuration) {
        return new AuthAPI(auth0Configuration.authRequestUrl(), auth0Configuration.clientId(), auth0Configuration.clientSecret());
    }

    public ManagementAPI managementAPI(Auth0Configuration auth0Configuration, String apiToken) {
        return new ManagementAPI(auth0Configuration.domain(), apiToken);
    }

}
