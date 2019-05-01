# Auth0 oauth authorization plugin for GoCD

## Requirements

* GoCD server version v17.5.0 or above
* Auth0 oauth application's `ClientId` and `ClientSectret`

## Installation

Copy the file `build/libs/auth0-authorization-plugin-VERSION.jar` to the GoCD server under `${GO_SERVER_DIR}/plugins/external`
and restart the server. The `GO_SERVER_DIR` is usually `/var/lib/go-server` on Linux and `C:\Program Files\Go Server` 
on Windows.

## Configuration

###  Create Auth0 oauth application

1. Login into your Auth0 account
2. Register a new application
7. Note down the `Client ID` and `Client Secret` of your application.

### Create Authorization Configuration

1. Login to `GoCD server` as admin and navigate to **_Admin_** _>_ **_Security_** _>_ **_Authorization Configuration_**
2. Click on **_Add_** to create new authorization configuration
    1. Specify `id` for auth config
    2. Select `Auth0 authorization plugin for GoCD` for **_Plugin id_**
    3. Choose `Auth0` or `Auth0 Enterprise` for `Authenticate with`.
    5. Specify **_Client ID_** and **_Client Secret_** and **_Domain_**
    7. Save your configuration