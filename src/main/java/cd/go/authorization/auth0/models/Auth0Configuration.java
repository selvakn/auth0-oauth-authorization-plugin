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

package cd.go.authorization.auth0.models;

import cd.go.authorization.auth0.annotation.ProfileField;
import cd.go.authorization.auth0.annotation.Validatable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

import java.util.Map;
import java.util.Objects;

import static cd.go.authorization.auth0.utils.Util.GSON;

public class Auth0Configuration implements Validatable {
    @Expose
    @SerializedName("ClientId")
    @ProfileField(key = "ClientId", required = true, secure = true)
    private String clientId;

    @Expose
    @SerializedName("ClientSecret")
    @ProfileField(key = "ClientSecret", required = true, secure = true)
    private String clientSecret;

    @Expose
    @SerializedName("Domain")
    @ProfileField(key = "Domain", required = true, secure = false)
    private String domain;

    @Expose
    @SerializedName("CallbackURL")
    @ProfileField(key = "CallbackURL", required = true, secure = false)
    private String callbackURL;

    public Auth0Configuration() {
    }

    public Auth0Configuration(String clientId, String clientSecret, String domain, String callbackURL) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.domain = domain;
        this.callbackURL = callbackURL;
    }

    public String clientId() {
        return clientId;
    }

    public String clientSecret() {
        return clientSecret;
    }

    public String domain() {
        return domain;
    }

    public String callbackURL() {
        return callbackURL;
    }

    public String toJSON() {
        return GSON.toJson(this);
    }

    public String authRequestUrl() {
        return String.format("https://%s", this.domain);
    }

    public String scope() {
        return "openid email profile";
    }

    public static Auth0Configuration fromJSON(String json) {
        return GSON.fromJson(json, Auth0Configuration.class);
    }

    public Map<String, String> toProperties() {
        return GSON.fromJson(toJSON(), new TypeToken<Map<String, String>>() {
        }.getType());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Auth0Configuration that = (Auth0Configuration) o;
        return Objects.equals(clientId, that.clientId) &&
                Objects.equals(clientSecret, that.clientSecret) &&
                Objects.equals(domain, that.domain);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clientId, clientSecret, domain);
    }
}

