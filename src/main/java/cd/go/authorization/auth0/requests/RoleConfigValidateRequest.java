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

package cd.go.authorization.auth0.requests;

import cd.go.authorization.auth0.executors.RequestExecutor;
import cd.go.authorization.auth0.models.Auth0RoleConfiguration;
import com.thoughtworks.go.plugin.api.request.GoPluginApiRequest;

public class RoleConfigValidateRequest extends Request {
    private final Auth0RoleConfiguration auth0RoleConfiguration;

    public RoleConfigValidateRequest(Auth0RoleConfiguration auth0RoleConfiguration) {
        this.auth0RoleConfiguration = auth0RoleConfiguration;
    }

    @Override
    public RequestExecutor executor() {
        return new RoleConfigValidateRequestExecutor(this);
    }

    public Auth0RoleConfiguration auth0RoleConfiguration() {
        return auth0RoleConfiguration;
    }

    public static final RoleConfigValidateRequest from(GoPluginApiRequest apiRequest) {
        return new RoleConfigValidateRequest(Auth0RoleConfiguration.fromJSON(apiRequest.requestBody()));
    }

}
