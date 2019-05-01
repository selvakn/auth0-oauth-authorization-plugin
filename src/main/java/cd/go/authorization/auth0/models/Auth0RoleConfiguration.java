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

import java.util.List;
import java.util.Map;
import java.util.Objects;

import static cd.go.authorization.auth0.utils.Util.*;

public class Auth0RoleConfiguration implements Validatable {

    @Expose
    @SerializedName("Users")
    @ProfileField(key = "Users", required = false, secure = false)
    private String users;

    public List<String> users() {
        return listFromCommaSeparatedString(toLowerCase(users));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Auth0RoleConfiguration that = (Auth0RoleConfiguration) o;
        return Objects.equals(users, that.users);
    }

    @Override
    public int hashCode() {
        return Objects.hash(users);
    }

    public String toJSON() {
        return GSON.toJson(this);
    }

    public static Auth0RoleConfiguration fromJSON(String json) {
        return GSON.fromJson(json, Auth0RoleConfiguration.class);
    }

    @Override
    public Map<String, String> toProperties() {
        return GSON.fromJson(toJSON(), new TypeToken<Map<String, String>>() {
        }.getType());
    }

    public boolean hasConfiguration() {
        return isNotBlank(users);
    }
}
