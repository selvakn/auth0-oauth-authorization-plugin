/*
 * Copyright 2019 ThoughtWorks, Inc.
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

import cd.go.authorization.auth0.requests.ValidateUserRequest;
import com.thoughtworks.go.plugin.api.response.DefaultGoPluginApiResponse;
import com.thoughtworks.go.plugin.api.response.GoPluginApiResponse;

import static cd.go.authorization.auth0.Auth0Plugin.LOG;
import static java.lang.String.format;

public class ValidateUserRequestExecutor implements RequestExecutor {
    private ValidateUserRequest request;

    public ValidateUserRequestExecutor(ValidateUserRequest request) {
        this.request = request;
    }

    @Override
    public GoPluginApiResponse execute() {
        LOG.info(format("[Is Valid User] %s is valid user.", request.getUsername()));
        return DefaultGoPluginApiResponse.success("");
    }
}
