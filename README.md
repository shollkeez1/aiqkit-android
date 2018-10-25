AIQ aiqkit Android SDK
-----------------

The Android SDK gives you access to the powerful AIQ Vision Search platform to integrate into your Android app.

#### AppID and Ingestion

Before using the sdk, an AppID/Secret pair is required. This can be obtained from the client portal at https://client.aiq.tech/. Note the staging server https://client.staging.aiq.tech/ is for trial users. You also need to ingest some image/pdf/video before you can search for them.

#### Add aiqkit as a Dependency.

add to your build.gradle:

```
dependencies {
    compile 'tech.aiq:aiqkit:0.9.6'
}
```

make sure you have the jcenter repo in your top level (project) build.gradle:

```
allprojects {
    repositories {
        jcenter()
    }
}
```

#### Initlaize the SDK with AppID and Secret

In your Application class, initialise the SDK with AppID and Secret
 
```
AIQKit.init(context, "AppID", "Secret", serviceUrl);
```

To use the example apps, define the following in your gradle.properties file:

```
AIQ_APP_ID=enter AppID here
AIQ_APP_SECRET=enter Secret here
```

#### Usage

Please see the sdk document and the sample apps for usage.


#### Proguard

The sdk support proguard and include a custom proguard file. User just need to enable proguard.

On a side note, the sdk depends on zbar barcode scanner lib, to reduce the final app size, unused *.so file can be removed from the packaging. Please see the build.gradle of the sample apps. 
