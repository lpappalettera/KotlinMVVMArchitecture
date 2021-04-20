# Boilerplate Android

Sample project that contains [Google's recommended app architecture](https://developer.android.com/jetpack/guide?gclid=Cj0KCQjw9_mDBhCGARIsAN3PaFOLBytu_oVS_e6q5uFSkcrURRH1zifwCmzpE5Mmq4S2J1Jpi21Ix1IaAueuEALw_wcB&gclsrc=aw.ds).

![Architecture overview](https://developer.android.com/topic/libraries/architecture/images/final-architecture.png)

#### Setup
The app uses [NewsApi](https://newsapi.org/) as a data source. The api requires an APIKey which should be configured in your local.properties file as following:
`newsApiKeyDev = <devProductFlavorApiKey>`
`newsApiKeyTst = <tstProductFlavorApiKey>`
`newsApiKeyAcc = <accProductFlavorApiKey>`
`newsApiKeyPrd = <prdProductFlavorApiKey>`

#### TODO
* Replace Moshi with [Kotlin Serialization](https://blog.jetbrains.com/kotlin/2020/10/kotlinx-serialization-1-0-released/)
* Add Unit Tests
* Add UI Tests
