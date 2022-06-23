# Boilerplate Android

A sample project setup that contains [Google's recommended app architecture](https://developer.android.com/jetpack/guide). The project deviates from the recommended app architecture for the UI since it uses Jetpack Compose.  The recommended app architecture has not been updated to accommodate Jetpack Compose yet.

![Architecture overview](https://developer.android.com/topic/libraries/architecture/images/final-architecture.png)

#### Setup
To use the Dictu internal Maven repository, you need to create a personal access token and add it in:
`~/.gradle/gradle.properties` by adding a line:
```
gitlab.token=<your gitlab token>
```

The token can be created from your [profile page](https://lab.dtnr.nl/-/profile/personal_access_tokens) on Gitlab. Make sure to give it a reasonable expiration date, and remember 
to tick the box that says: "read_api"

The app uses [News Api](https://newsapi.org/) as a data source. The api requires an API key which should be configured in your local.properties file as following:

```
newsApiKeyDev = <devProductFlavorApiKey>
newsApiKeyTst = <tstProductFlavorApiKey>
newsApiKeyAcc = <accProductFlavorApiKey>
newsApiKeyPrd = <prdProductFlavorApiKey>
```

#### TODO
* Add Unit Tests
* Add UI Tests
