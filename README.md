# Auth0 OAuth authorization plugin for GoCD

The plugin allows user to login in GoCD using an Auth0 tenant. Authorization based on roles is not yet suported.

# Todo
- [x] Authentication
- [ ] Authorization using Auth0 configured roles

# Installation

Documentation for installation is available [here](INSTALL.md)

# Capabilities

* The plugin is implemented using `GoCD plugin authorization endpoint`.

## Troubleshooting

Add environment variable `GO_SERVER_SYSTEM_PROPERTIES=-Dplugin.cd.go.authorization.auth0.log.level=debug` to enable debug log. The plugin logs are written to `LOG_DIR/plugin-cd.go.authorization.auth0.log`. The log dir on Linux is `/var/log/go-server`; logs on Windows are written to `C:\Program Files\Go Server\logs`; logs on docker images are written to `/godata/logs`.


## Building the code base

To build the jar, run `./gradlew clean test assemble`
