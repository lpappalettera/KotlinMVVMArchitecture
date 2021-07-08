# Boilerplate Android

A sample project setup that contains [Google's recommended app architecture](https://developer.android.com/jetpack/guide). The project deviates from the recommended app architecture for the UI since it uses Jetpack Compose.  The recommended app architecture has not been updated to accommodate Jetpack Compose yet.

![Architecture overview](https://developer.android.com/topic/libraries/architecture/images/final-architecture.png)

#### Setup
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
