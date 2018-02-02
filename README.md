AIQ image match Android SDK
-----------------

The Android SDK gives you access to the powerful AIQ Vision Search platform
to integrate into your Android app.

Installation
------------

#### With Gradle

add to your build.gradle:

```
dependencies {
    compile 'tech.aiq:aiqkit:0.9.4'
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

Initialization
---------------

#### Required

In your Application class, initialise the SDK with the App ID and secret you obtained from developer.aiq.tech
 
```
AIQKit.init(context, "APP-ID", "APP-SECRET", service_url);
```

To use the example apps, define the following in your global gradle.properties file:

```
AIQ_APP_ID=enter app id here
AIQ_APP_SECRET=enter app secret here
```

Usage
-----

Please see the sdk document and the sample application for usage.

Proguard
-------
the aiq image sdk support proguard and include a custom proguard file. User just need to enable proguard.

On a side note, the sdk depends on zbar barcode scanner lib, to reduce the final app size, unused *.so file can be removed from the packaging. Please see the build.gradle of the sample app. 
