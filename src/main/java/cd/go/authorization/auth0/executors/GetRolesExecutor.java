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

import cd.go.authorization.auth0.Auth0ClientBuilder;
import cd.go.authorization.auth0.requests.GetRolesRequest;
import com.thoughtworks.go.plugin.api.response.DefaultGoPluginApiResponse;
import com.thoughtworks.go.plugin.api.response.GoPluginApiResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static cd.go.authorization.auth0.Auth0Plugin.LOG;
import static cd.go.authorization.auth0.utils.Util.GSON;
import static java.lang.String.format;

public class GetRolesExecutor implements RequestExecutor {
    private final GetRolesRequest request;
    private Auth0ClientBuilder clientBuilder;

    public GetRolesExecutor(GetRolesRequest request) {
        this(request, new Auth0ClientBuilder());
    }

    GetRolesExecutor(GetRolesRequest request, Auth0ClientBuilder clientBuilder) {
        this.request = request;
        this.clientBuilder = clientBuilder;
    }

    @Override
    public GoPluginApiResponse execute() throws IOException {
        if (request.getRoles().isEmpty()) {
            LOG.debug("[Get User Roles] Server sent empty roles config. Nothing to do!.");
            return DefaultGoPluginApiResponse.success("[]");
        }


        List<String> roles = new ArrayList<>();

        LOG.debug(format("[Get User Roles] User %s has %s roles.", request.getUsername(), roles));
        return DefaultGoPluginApiResponse.success(GSON.toJson(roles));
    }
}

