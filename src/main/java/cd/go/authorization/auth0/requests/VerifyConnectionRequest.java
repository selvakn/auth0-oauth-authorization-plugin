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
import cd.go.authorization.auth0.executors.VerifyConnectionRequestExecutor;
import cd.go.authorization.auth0.models.Auth0Configuration;
import com.thoughtworks.go.plugin.api.request.GoPluginApiRequest;

public class VerifyConnectionRequest extends Request {
    private final Auth0Configuration configuration;

    private VerifyConnectionRequest(Auth0Configuration configuration) {
        this.configuration = configuration;
    }

    public static VerifyConnectionRequest from(GoPluginApiRequest apiRequest) {
        return new VerifyConnectionRequest(Auth0Configuration.fromJSON(apiRequest.requestBody()));
    }

    public Auth0Configuration config() {
        return configuration;
    }

    @Override
    public RequestExecutor executor() {
        return new VerifyConnectionRequestExecutor(this);
    }
}
